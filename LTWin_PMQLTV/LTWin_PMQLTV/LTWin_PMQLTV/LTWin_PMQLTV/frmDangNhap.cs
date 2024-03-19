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
using System.Security.Cryptography;

namespace LTWin_PMQLTV
{
    public partial class frmDangNhap : DevExpress.XtraEditors.XtraForm
    {
        public frmDangNhap()
        {
            InitializeComponent();
        }

        // khai báo kết nối
        private string chuoiKetNoi = ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
        private SqlConnection myConnection;
        private SqlCommand myCommand;

        // đóng form
        private void DongForm(object sender, FormClosedEventArgs e)
        {
            this.Close();
        }

        // phương thức kiểm tra điều kiện tài khoản
        private int kq = 0;
        private void kTraDK()
        {
            if (txtTenDangNhap.Text.Length > 0 && txtMatKhau.Text.Length > 0 && txtCapcha.Text.Length > 0)
                kq = 1;
        }

        // phương thức kiểm tra tài khoản
        private int x1 = 0;
        private string strMK = "";
        private void xacThucTKTT()
        {
            myConnection = new SqlConnection(chuoiKetNoi);
            myConnection.Open();
            string strCauTruyVan = @"
                SELECT COUNT(*) FROM AspNetUsers AS u
                INNER JOIN AspNetUserRoles AS ur ON u.Id = ur.UserId
                INNER JOIN AspNetRoles AS r ON ur.RoleId = r.Id
                WHERE u.Email = @acc AND u.PasswordHash = @pass AND r.Name = 'Admin'";
            //string strCauTruyVan = "select count(*) from AspNetUsers where Email=@acc and PasswordHash=@pass";
            myCommand = new SqlCommand(strCauTruyVan, myConnection);
            myCommand.Parameters.Add(new SqlParameter("@acc", txtTenDangNhap.Text));
            myCommand.Parameters.Add(new SqlParameter("@pass", strMK));
            x1 = (int)myCommand.ExecuteScalar();
            myConnection.Close();
        }

        private void frmLogIn_Load(object sender, EventArgs e)
        {
            txtTenDangNhap.Focus();
        }

        private string _captchaText;

        /// <summary>
        /// đưa c.trình về trạng thái mặc định
        /// </summary>
        private void Reset()
        {
            _captchaText = this.RandomString();
            txtCapcha.Text = "";
            panel1.BackgroundImage = drawImage(_captchaText, panel1.Width, panel1.Height);
        }

        /// <summary>
        /// trả về hình ảnh
        /// </summary>
        /// <param name="txt">chuỗi cần vẽ lên ảnh</param>
        /// <param name="With"></param>
        /// <param name="Height"></param>
        /// <returns>ảnh trả về</returns>
        private Bitmap drawImage(string txt, int With, int Height)
        {
            Bitmap bt = new Bitmap(Width, Height);
            Graphics g = Graphics.FromImage(bt);
            SolidBrush sb = new SolidBrush(Color.White);
            g.FillRectangle(sb, 0, 0, bt.Width, bt.Height);
            System.Drawing.Font font = new System.Drawing.Font("Arial", 25);

            sb = new SolidBrush(Color.Black);
            g.DrawString(txt, font, sb, 0, 0);

            int count = 0;
            Random rand = new Random();
            while (count < 100)
            {
                g.FillEllipse(sb, rand.Next(0, bt.Width), rand.Next(0, bt.Height), 7, 7);
                count++;
            }
            return bt;
        }

        /// <summary>
        /// trả về chuỗi 5 kí tự gồm số lần chữ
        /// </summary>
        /// <returns></returns>
        private String RandomString()
        {
            Random rand = new Random();
            string str = getMD5(rand.Next().ToString());
            return str = str.Substring(str.Length - 5);
        }

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

        // hàm kiểm tra form
        private Form kiemtraform(Type ftype)
        {
            foreach (Form f in this.MdiChildren)
            {
                if (f.GetType() == ftype)
                {
                    return f;
                }
            }
            return null;
        }

        // bool bLogin = true;
        private void btnDangNhap_Click(object sender, EventArgs e)
        {
            strMK = getMD5(txtMatKhau.Text);
            kTraDK();
            if (kq == 1)
            {
                if (txtCapcha.Text.ToUpper() == _captchaText.ToUpper())
                {
                    try
                    {
                        xacThucTKTT();
                        if (x1 == 1)
                        {

                            frmMain frmChinh = new frmMain();
                            frmChinh.Show();
                            frmChinh.Login();

                            txtTenDangNhap.Text = "";
                            txtMatKhau.Text = "";
                            txtCapcha.Text = "";
                            Reset();

                            // đóng form
                            Form fromSearch = kiemtraform(typeof(frmDangNhap));
                            if (fromSearch == null)
                            {
                                //frmCreateAcc forms = new frmCreateAcc();
                                //forms.MdiParent = this;
                                //forms.Show();
                            }
                            else
                            {
                                fromSearch.Close();
                            }
                            MessageBox.Show("Đăng nhập thành công.", "Thông Báo");
                            frmChinh.FormClosed += new FormClosedEventHandler(DongForm);
                        }
                        else
                        {
                            MessageBox.Show("Sai tên đăng nhập hoặc mật khẩu.\nVui lòng nhập lại.", "Thông Báo");
                            txtTenDangNhap.Text = "";
                            txtMatKhau.Text = "";
                            txtTenDangNhap.Focus();
                        }
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show(ex.Message);
                    }
                }
                else
                    MessageBox.Show("Vui lòng nhập đúng mã CAPTCHA.", "Thông Báo");
                Reset();
            }
            else
                MessageBox.Show("Vui lòng nhập tài Khoản và mật Khẩu và mã CAPTCHA.", "Thông Báo");
        }

        private void btnThoat_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void txtMatKhau_MouseDown(object sender, MouseEventArgs e)
        {
            txtMatKhau.Properties.UseSystemPasswordChar = false;
        }

        private void txtMatKhau_MouseUp(object sender, MouseEventArgs e)
        {
            txtMatKhau.Properties.UseSystemPasswordChar = true;
        }

        private void frmDangNhap_Load(object sender, EventArgs e)
        {
            txtTenDangNhap.Text = "adminT@gmail.com";
            txtMatKhau.Text = "Tung@123";

            Reset();
        }

        private void simpleButton1_Click(object sender, EventArgs e)
        {
            Reset();
        }

        private void txtTenDangNhap_EditValueChanged(object sender, EventArgs e)
        {

        }

        private void txtMatKhau_EditValueChanged(object sender, EventArgs e)
        {

        }
    }
}