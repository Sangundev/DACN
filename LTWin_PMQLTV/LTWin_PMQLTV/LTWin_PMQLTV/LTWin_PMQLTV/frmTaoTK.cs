using System;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;
using System.Globalization;
using System.Security.Cryptography;
using System.Text;
using System.Windows.Forms;
using DevExpress.XtraEditors;

namespace LTWin_PMQLTV
{
    public partial class frmTaoTK : DevExpress.XtraEditors.XtraForm
    {
        public frmTaoTK()
        {
            InitializeComponent();
        }

        // khai báo kết nối
        private string strKetNoi = ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
        private SqlConnection myConnection; // kết nối tới csdl
        private SqlDataAdapter myDataAdapter;   // chuyển csdl qua DataTable
        private DataTable myTable;  // lưu bảng lấy từ c#
        private SqlCommand myCommand;   // thực hiện các lệnh truy vấn

        public string strDK = "Lỗi:";

        // phương thức kết nối
        private DataTable ketnoi(string truyvan)
        {
            myConnection = new SqlConnection(strKetNoi);
            myConnection.Open();
            string thuchiencaulenh = truyvan;
            myCommand = new SqlCommand(thuchiencaulenh, myConnection);
            myDataAdapter = new SqlDataAdapter(myCommand);
            myTable = new DataTable();
            myDataAdapter.Fill(myTable);
            return myTable;
        }

        private void frmCreateAcc_Load(object sender, EventArgs e)
        {
            txtMaNV.Text = Guid.NewGuid().ToString();
            //txtMaNV.Text = TangMaTuDong();
        }

        private void txtMK_Properties_MouseDown(object sender, MouseEventArgs e)
        {
            txtMK.Properties.UseSystemPasswordChar = false;
        }

        private void txtMK_Properties_MouseUp(object sender, MouseEventArgs e)
        {
            txtMK.Properties.UseSystemPasswordChar = true;
        }

        private void txtNhapLaiMK_Properties_MouseDown(object sender, MouseEventArgs e)
        {
            txtNhapLaiMK.Properties.UseSystemPasswordChar = false;
        }

        private void txtNhapLaiMK_Properties_MouseUp(object sender, MouseEventArgs e)
        {
            txtNhapLaiMK.Properties.UseSystemPasswordChar = true;
        }

        private void btnCreatAcc_Click(object sender, EventArgs e)
        {
            KiemTraDangKy();
            if (strDK == "Lỗi:")
            {
                string mkMD5 = getMD5(txtMK.Text);

                try
                {
                    string gioiTinh = radGTNam.Checked ? "True" : "False";
                    string maTaiKhoan = Guid.NewGuid().ToString();  // sử dụng GUID

                    string themDongSql =
                        "INSERT INTO AspNetUsers " +
                        "(Id, Email, EmailConfirmed, PasswordHash, SecurityStamp, PhoneNumber, PhoneNumberConfirmed, TwoFactorEnabled, LockoutEndDateUtc, LockoutEnabled, AccessFailedCount, UserName, IsApproved, Adress, status, Opentime, Closetime, Fullname, GioiTinh, NgaySinh, GhiChu) " +
                        "VALUES " +
                        "(@Id, @Email, @EmailConfirmed, @PasswordHash, @SecurityStamp, @PhoneNumber, @PhoneNumberConfirmed, @TwoFactorEnabled, @LockoutEndDateUtc, @LockoutEnabled, @AccessFailedCount, @Username, @IsApproved, @Adress, @Status, @OpenTime, @CloseTime, @FullName, @GioiTinh, @NgaySinh, @GhiChu)";

                    myConnection = new SqlConnection(strKetNoi);
                    myConnection.Open();

                    SqlCommand cmd = new SqlCommand(themDongSql, myConnection);
                    cmd.Parameters.AddWithValue("@Id", maTaiKhoan);   // sử dụng GUID
                    cmd.Parameters.AddWithValue("@Email", txtTenTK.Text);
                    cmd.Parameters.AddWithValue("@EmailConfirmed", false);
                    cmd.Parameters.AddWithValue("@PasswordHash", getMD5(txtMK.Text));
                    cmd.Parameters.AddWithValue("@SecurityStamp", Guid.NewGuid().ToString());
                    cmd.Parameters.AddWithValue("@PhoneNumber", txtSoDT.Text);
                    cmd.Parameters.AddWithValue("@PhoneNumberConfirmed", false);
                    cmd.Parameters.AddWithValue("@TwoFactorEnabled", false);
                    cmd.Parameters.AddWithValue("@LockoutEndDateUtc", DBNull.Value);
                    cmd.Parameters.AddWithValue("@LockoutEnabled", true);
                    cmd.Parameters.AddWithValue("@AccessFailedCount", 0);
                    cmd.Parameters.AddWithValue("@Username", txtTenNV.Text);
                    cmd.Parameters.AddWithValue("@IsApproved", true);
                    //cmd.Parameters.AddWithValue("@Image", DBNull.Value);
                    cmd.Parameters.AddWithValue("@Adress", txtDiaChi.Text);
                    cmd.Parameters.AddWithValue("@Status", DBNull.Value);
                    cmd.Parameters.AddWithValue("@OpenTime", DBNull.Value);
                    cmd.Parameters.AddWithValue("@CloseTime", DBNull.Value);
                    cmd.Parameters.AddWithValue("@FullName", txtTenNV.Text);
                    cmd.Parameters.AddWithValue("@GioiTinh", gioiTinh);
                    cmd.Parameters.AddWithValue("@NgaySinh", DateTime.ParseExact(dtmNgaySinh.Text, "dd/MM/yyyy", CultureInfo.InvariantCulture));
                    cmd.Parameters.AddWithValue("@GhiChu", txtGhiChu.Text);

                    cmd.ExecuteNonQuery();
                    myConnection.Close();

                    MessageBox.Show("Tạo tài khoản thành công.", "Thông Báo");
                    this.Close();
                    //XoaDuLieuSauKhiThem();
                }
                catch (Exception ex)
                {
                    MessageBox.Show("Tạo tài khoản thất bại. Lỗi: " + ex.Message);
                }
                finally
                {
                    myConnection.Close();
                }
            }
            else
            {
                //strDK += "\nVui lòng nhập đầy đủ thông tin.";
                MessageBox.Show(strDK);
                FocusOnFirstEmptyField();
            }
            strDK = "Lỗi";
        }

        private void FocusOnFirstEmptyField()
        {
            if (string.IsNullOrEmpty(txtTenNV.Text))
                txtTenNV.Focus();
            else if (string.IsNullOrEmpty(txtSoDT.Text))
                txtSoDT.Focus();
            else if (string.IsNullOrEmpty(txtDiaChi.Text))
                txtDiaChi.Focus();
            else if (string.IsNullOrEmpty(txtTenTK.Text))
                txtTenTK.Focus();
            else if (string.IsNullOrEmpty(txtMK.Text))
                txtMK.Focus();
            else if (string.IsNullOrEmpty(txtNhapLaiMK.Text))
                txtNhapLaiMK.Focus();
        }

        private void KiemTraDangKy()
        {
            strDK = "Lỗi:";

            if (string.IsNullOrEmpty(txtTenNV.Text))
                strDK += "\n- Chưa nhập tên nhân viên.";

            // Kiểm tra giới tính
            if (!radGTNam.Checked && !radGTNu.Checked)
                strDK += "\n- Chưa chọn giới tính.";

            // Kiểm tra ngày tháng năm sinh
            DateTime ngaySinh;
            if (!DateTime.TryParseExact(dtmNgaySinh.Text, "dd/MM/yyyy", CultureInfo.InvariantCulture, DateTimeStyles.None, out ngaySinh))
                strDK += "\n- Ngày tháng năm sinh không hợp lệ.";

            if (string.IsNullOrEmpty(txtSoDT.Text))
                strDK += "\n- Chưa nhập số điện thoại.";
            else
            {
                long kq;
                if (!long.TryParse(txtSoDT.Text, out kq))
                    strDK += "\n- Số điện thoại không đúng.";
            }

            if (string.IsNullOrEmpty(txtDiaChi.Text))
                strDK += "\n- Chưa nhập địa chỉ.";

            if (string.IsNullOrEmpty(txtTenTK.Text))
                strDK += "\n- Chưa nhập tên tài khoản.";

            if (string.IsNullOrEmpty(txtMK.Text))
                strDK += "\n- Chưa nhập mật khẩu.";

            if (string.IsNullOrEmpty(txtNhapLaiMK.Text))
                strDK += "\n- Chưa nhập mật khẩu lần 2.";

            if (!string.IsNullOrEmpty(txtMK.Text) && !string.IsNullOrEmpty(txtNhapLaiMK.Text) && !txtMK.Text.Equals(txtNhapLaiMK.Text))
                strDK += "\n- Mật khẩu lần 2 phải giống lần 1.";
        }


        private string TangMaTuDong()
        {
            myConnection = new SqlConnection(strKetNoi);
            myConnection.Open();
            string cauTruyVan = "SELECT * FROM AspNetUsers";
            ketnoi(cauTruyVan);
            myConnection.Close();
            string maTuDong = "";

            if (myTable.Rows.Count <= 0)
            {
                maTuDong = "NV001";
            }
            else
            {
                int k = 0;
                if (myTable.Rows[myTable.Rows.Count - 1][0].ToString().Length >= 5)
                {
                    if (int.TryParse(myTable.Rows[myTable.Rows.Count - 1][0].ToString().Substring(2, 3), out k))
                    {
                        k++;
                    }
                }

                maTuDong = "NV" + k.ToString("D3");
            }

            return maTuDong;
        }

        //private void XoaDuLieuSauKhiThem()
        //{
        //    txtTenNV.Text = "";
        //    dtmNgaySinh.Text = "";
        //    radGTNam.Checked = true;
        //    radGTNu.Checked = false;
        //    txtSoDT.Text = "";
        //    txtDiaChi.Text = "";
        //    txtTenTK.Text = "";
        //    txtMK.Text = "";
        //    txtNhapLaiMK.Text = "";
        //    txtGhiChu.Text = "";
        //}

        private string getMD5(string txt)
        {
            string str = "";
            byte[] buffer = Encoding.UTF8.GetBytes(txt);
            using (MD5CryptoServiceProvider md5 = new MD5CryptoServiceProvider())
            {
                buffer = md5.ComputeHash(buffer);
                foreach (byte b in buffer)
                {
                    str += b.ToString("X2");
                }
            }
            return str;
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void txtMK_EditValueChanged(object sender, EventArgs e)
        {

        }
    }
}
