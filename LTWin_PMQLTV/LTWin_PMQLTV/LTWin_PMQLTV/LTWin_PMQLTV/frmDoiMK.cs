using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using DevExpress.XtraEditors;
using System.Data.SqlClient;
using System.Configuration;
using System.IO;

namespace LTWin_PMQLTV
{
    public partial class frmDoiMK : DevExpress.XtraEditors.XtraForm
    {
        public frmDoiMK()
        {
            InitializeComponent();
        }

        private string chuoiKetNoi = ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
        private SqlConnection myConnection;
        private SqlCommand myCommand;

        // hàm mã hóa pass thành md5 32 ký tự
        private string getMD5(string txt)
        {
            string str = "";
            Byte[] buffer = System.Text.Encoding.UTF8.GetBytes(txt);
            System.Security.Cryptography.MD5CryptoServiceProvider md5 = new System.Security.Cryptography.MD5CryptoServiceProvider();
            buffer = md5.ComputeHash(buffer);
            foreach (Byte b in buffer)
            {
                str += b.ToString("X2");
            }
            return str;
        }

        private void btnDoiMatKhau_Click(object sender, EventArgs e)
        {
            if (txtTenTaiKhoan.Text.Length == 0)
            {
                MessageBox.Show("Bạn chưa nhập tên tài khoản.", "Thông Báo");
                txtTenTaiKhoan.Focus();
            }
            else if (txtMatKhau.Text.Length == 0)
            {
                MessageBox.Show("Bạn chưa nhập mật khẩu.", "Thông Báo");
                txtMatKhau.Focus();
            }
            else if (txtMatKhauMoi.Text.Length == 0)
            {
                MessageBox.Show("Bạn chưa nhập mật khẩu mới.", "Thông Báo");
                txtMatKhauMoi.Focus();
            }
            else if (txtNhapLaiMKMoi.Text.Length == 0)
            {
                MessageBox.Show("Bạn chưa nhập lại mật khẩu mới.", "Thông Báo");
                txtNhapLaiMKMoi.Focus();
            }
            string mk1, mk2;
            mk1 = txtMatKhauMoi.Text;
            mk2 = txtNhapLaiMKMoi.Text;
            int kq = mk1.CompareTo(mk2);
            if (txtTenTaiKhoan.Text.Length > 0 && txtMatKhau.Text.Length > 0 && txtMatKhauMoi.Text.Length > 0 && txtNhapLaiMKMoi.Text.Length > 0)
            {
                try
                {
                    string strMK = getMD5(txtMatKhau.Text);
                    myConnection = new SqlConnection(chuoiKetNoi);
                    myConnection.Open();
                    string strCauTruyVan = "select count(*) from AspNetUsers where Email=@acc and PasswordHash=@pass";
                    myCommand = new SqlCommand(strCauTruyVan, myConnection);
                    myCommand.Parameters.Add(new SqlParameter("@acc", txtTenTaiKhoan.Text));
                    myCommand.Parameters.Add(new SqlParameter("@pass", strMK));
                    int x = (int)myCommand.ExecuteScalar();
                    myConnection.Close();
                    if (x == 1)
                    {
                        if (kq == 0)
                        {
                            string strMKMoi = getMD5(txtMatKhauMoi.Text);
                            string luumk = "update AspNetUsers set PasswordHash='" + strMKMoi + "' where Email='" + txtTenTaiKhoan.Text + "'";
                            myConnection = new SqlConnection(chuoiKetNoi);
                            myConnection.Open();
                            myCommand = new SqlCommand(luumk, myConnection);
                            myCommand.ExecuteNonQuery();
                            myConnection.Close();
                            MessageBox.Show("Đổi mật khẩu thành công.", "Thông Báo");
                            txtTenTaiKhoan.Text = "";
                            txtMatKhau.Text = "";
                            txtMatKhauMoi.Text = "";
                            txtNhapLaiMKMoi.Text = "";
                            txtTenTaiKhoan.Focus();
                        }
                        else
                        {
                            MessageBox.Show("Mật khẩu mới không khớp nhau.", "Thông Báo");
                            txtMatKhauMoi.Text = "";
                            txtNhapLaiMKMoi.Text = "";
                            txtMatKhauMoi.Focus();
                        }

                        //MessageBox.Show("Đăng Nhập thành công.", "Thông Báo");
                        //frmGiaoDienChinh GiaoDienChinh = new frmGiaoDienChinh();
                        //GiaoDienChinh.FormClosed += new FormClosedEventHandler(DongForm);
                        //this.Hide();
                        //GiaoDienChinh.Show();
                    }
                    else
                    {
                        MessageBox.Show("Sai tên đăng nhập hoặc mật khẩu.\nVui lòng nhập lại.", "Thông Báo");
                        txtTenTaiKhoan.Text = "";
                        txtMatKhau.Text = "";
                        txtMatKhauMoi.Text = "";
                        txtNhapLaiMKMoi.Text = "";
                        txtTenTaiKhoan.Focus();
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
        }

        private void btnThoat_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void txtMatKhau_Properties_MouseDown(object sender, MouseEventArgs e)
        {
            txtMatKhau.Properties.UseSystemPasswordChar = false;
        }

        private void txtMatKhau_Properties_MouseUp(object sender, MouseEventArgs e)
        {
            txtMatKhau.Properties.UseSystemPasswordChar = true;
        }

        private void txtMatKhauMoi_Properties_MouseDown(object sender, MouseEventArgs e)
        {
            txtMatKhauMoi.Properties.UseSystemPasswordChar = false;
        }

        private void txtMatKhauMoi_Properties_MouseUp(object sender, MouseEventArgs e)
        {
            txtMatKhauMoi.Properties.UseSystemPasswordChar = true;
        }

        private void txtNhapLaiMKMoi_Properties_MouseDown(object sender, MouseEventArgs e)
        {
            txtNhapLaiMKMoi.Properties.UseSystemPasswordChar = false;
        }

        private void txtNhapLaiMKMoi_Properties_MouseUp(object sender, MouseEventArgs e)
        {
            txtNhapLaiMKMoi.Properties.UseSystemPasswordChar = true;
        }

        private void frmDoiMK_Load(object sender, EventArgs e)
        {

        }

        private void txtMatKhau_EditValueChanged(object sender, EventArgs e)
        {

        }
    }
}