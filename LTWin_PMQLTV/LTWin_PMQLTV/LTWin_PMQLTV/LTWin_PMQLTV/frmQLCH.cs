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
using DevExpress.XtraReports.UI;
using System.Drawing.Imaging;
using System.Globalization;
using DevExpress.CodeParser;
using DevExpress.DataProcessing.InMemoryDataProcessor;
using DevExpress.XtraGrid.Views.Base.ViewInfo;
using System.Reflection;
using DevExpress.XtraGrid;

namespace LTWin_PMQLTV
{
    public partial class frmQLCH : DevExpress.XtraEditors.XtraForm
    {
        public frmQLCH()
        {
            InitializeComponent();           
        }

        // Khai báo kết nối
        private string strKetNoi = ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
        private SqlConnection myConnection; // Kết nối tới csdl
        private SqlDataAdapter myDataAdapter;   // Vận chuyển csdl qua DataSet
        private DataTable myTable;  // Dùng để lưu bảng lấy từ C#
        private SqlCommand myCommand;   // Thực hiện các lệnh truy vấn

        // Phương thức kết nối
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

        // thiết lập ẩn hiện controls
        private void setControls(bool edit)
        {
            txtTenCCH.ReadOnly = edit;
            txtEmail.ReadOnly = edit;
            txtSoDT.ReadOnly = edit;
            txtTenCH.ReadOnly = edit;
            txtDiaChi.ReadOnly = edit;
            txtGioMoCua.ReadOnly = edit;
            txtGioDongCua.ReadOnly = edit;
            txtMoTa.ReadOnly = edit;
            picAnhCCH.ReadOnly = edit;
        }

        // thiết lập ẩn hiện controls
        private void setControls2(bool edit)
        {
            dtmNgaySinh.Enabled = edit;
            radGTNam.Enabled = edit;
            radGTNu.Enabled = edit;

            ckbDangHD.Enabled = edit;
            ckbNgungHD.Enabled = edit;

            btnCamera.Enabled = edit;
            btnLoadImg.Enabled = edit;
            btnSave.Enabled = edit;
            btnCancel.Enabled = edit;
        }

        // thiết lập ẩn hiện controls
        private void setControls3(bool edit)
        {
            btnRefresh.Enabled = edit;
            btnAdd.Enabled = edit;
            btnEdit.Enabled = edit;
            btnDelete.Enabled = edit;
        }

        // chuyển byte thành ảnh
        public Image ByteArrayToImage(byte[] byteArrayIn)
        {
            MemoryStream ms = new MemoryStream(byteArrayIn);
            Image returnImage = Image.FromStream(ms);
            return returnImage;
        }

        // Load dữ liệu từ gridControls lên controls
        public string strMaCH, strTenCCH, strEmail, strSoDT, strNgaySinh, strGioiTinh, strTenCH, strDiaChi, strGioMoCua, strGioDongCua, strMoTa, imgs, strTrangThai;
        public Image myImage = null;
        public int row;

        private void GridView()
        {
            txtMaCH.Text = myTable.Rows[row]["Id"].ToString();
            strMaCH = txtMaCH.Text;

            txtTenCCH.Text = myTable.Rows[row]["Fullname"].ToString();
            strTenCCH = txtTenCCH.Text;

            txtEmail.Text = myTable.Rows[row]["Email"].ToString();
            strEmail = txtGioMoCua.Text;

            txtSoDT.Text = myTable.Rows[row]["PhoneNumber"].ToString();
            strSoDT = txtSoDT.Text;

            dtmNgaySinh.Text = myTable.Rows[row]["NgaySinh"].ToString();
            strNgaySinh = dtmNgaySinh.Text;

            // Kiểm tra và gán giá trị cho radio button giới tính
            strGioiTinh = myTable.Rows[row]["GioiTinh"] != DBNull.Value ? myTable.Rows[row]["GioiTinh"].ToString() : string.Empty;
            if (!string.IsNullOrEmpty(strGioiTinh))
            {
                if (string.Equals(strGioiTinh, "True", StringComparison.OrdinalIgnoreCase))
                {
                    radGTNam.Checked = true;
                }
                else
                {
                    radGTNu.Checked = true;
                }
            }
            
            txtTenCH.Text = myTable.Rows[row]["StoreName"].ToString();
            strTenCH = txtTenCH.Text;

            txtDiaChi.Text = myTable.Rows[row]["Adress"].ToString();
            strDiaChi = txtDiaChi.Text;

            txtGioMoCua.Text = myTable.Rows[row]["Opentime"].ToString();
            strDiaChi = txtDiaChi.Text;

            txtGioDongCua.Text = myTable.Rows[row]["Closetime"].ToString();
            strDiaChi = txtDiaChi.Text;

            txtMoTa.Text = myTable.Rows[row]["MoTa"].ToString();
            strMoTa = txtMoTa.Text;

            imgs = myTable.Rows[row]["Images"].ToString();
            if (imgs == "")
            {
                myImage = Image.FromFile("..\\cch\\user.png");
            }
            else
            {
                myImage = ByteArrayToImage((byte[])myTable.Rows[row]["Images"]);
            }
            picAnhCCH.Image = myImage;

            // Kiểm tra và gán giá trị cho radio button trạng thái
            strTrangThai = myTable.Rows[row]["IsApproved"] != DBNull.Value ? myTable.Rows[row]["IsApproved"].ToString() : string.Empty;
            if (!string.IsNullOrEmpty(strTrangThai))
            {
                if (string.Equals(strTrangThai, "True", StringComparison.OrdinalIgnoreCase))
                {
                    ckbDangHD.Checked = true;
                    ckbNgungHD.Checked = false; // Bỏ chọn checkbox kia
                }
                else
                {
                    ckbNgungHD.Checked = true;
                    ckbDangHD.Checked = false; // Bỏ chọn checkbox kia
                }
            }
        }

        // load form
        private void frmQLTKCCH_Load(object sender, EventArgs e)
        {
            string cauTruyVan = @"
                SELECT U.*
                FROM AspNetUsers U
                INNER JOIN AspNetUserRoles UR ON U.Id = UR.UserId
                INNER JOIN AspNetRoles R ON UR.RoleId = R.Id
                WHERE R.Name = 'User'";
            //string cauTruyVan = "select * from AspNetUsers";
            gridControlDSCCH.DataSource = ketnoi(cauTruyVan);
            myConnection.Close();
            txtMaCH.ReadOnly = true;
            setControls(true);
            setControls2(false);

            row = 0;
            GridView();
            txtFind.Focus();
        }

        // sự kiện row được chọn
        private void grdDSCCH_RowClick(object sender, DevExpress.XtraGrid.Views.Grid.RowClickEventArgs e)
        {
            row = e.RowHandle;
            GridView();
        }

        // Load ảnh lên picturebox
        string imgLocation = "";
        private byte[] images = null;
        private void btnLoadImg_Click(object sender, EventArgs e)
        {
            OpenFileDialog dlrOpen = new OpenFileDialog();
            dlrOpen.Filter = "All file(*.*)|*.*|Png files(*.png)|*.png|Jpg files(*.jpg)|*.jpg";
            if (dlrOpen.ShowDialog() == DialogResult.OK)
            {
                imgLocation = dlrOpen.FileName.ToString();
                Image img = Image.FromFile(imgLocation);
                picAnhCCH.Image = img;
            }
        }

        private void btnRefresh_Click(object sender, EventArgs e)
        {
            string cauTruyVan = @"
                SELECT U.*
                FROM AspNetUsers U
                INNER JOIN AspNetUserRoles UR ON U.Id = UR.UserId
                INNER JOIN AspNetRoles R ON UR.RoleId = R.Id
                WHERE R.Name = 'User'";
            //string cauTruyVan = "select * from AspNetUsers";
            gridControlDSCCH.DataSource = ketnoi(cauTruyVan);
            myConnection.Close();
            txtFind.Text = "";
            row = 0;
            GridView();
        }

        // nút add
        public int xuly = 2;
        private void btnAdd_Click(object sender, EventArgs e)
        {
            imgLocation = "..\\cch\\user.png";
            images = null;
            FileStream Streem = new FileStream(imgLocation, FileMode.Open, FileAccess.Read);
            BinaryReader brs = new BinaryReader(Streem);
            images = brs.ReadBytes((int)Streem.Length);
            xuly = 0;
            setControls(false);
            setControls2(true);
            setControls3(false);
            btnPrint.Enabled = false;
            txtFind.ReadOnly = true;

            txtMaCH.Text = Guid.NewGuid().ToString();
            txtTenCCH.Text = "";
            txtEmail.Text = "";
            txtSoDT.Text = "";
            dtmNgaySinh.Text = DateTime.Now.ToString("dd/MM/yyyy");
            radGTNam.Checked = true;
            txtTenCH.Text = "";
            txtDiaChi.Text = "";
            txtGioMoCua.Text = "";
            txtGioDongCua.Text = "";
            txtMoTa.Text = "";
            picAnhCCH.Image = null;
            ckbDangHD.Checked = true;

            gridControlDSCCH.Enabled = false;
            txtTenCCH.Focus();
        }

        // nút edit
        private bool isStatusChanged = false; // Biến để theo dõi xem trạng thái có thay đổi không
        private bool isStatusConfirmed = false;
        private void btnEdit_Click(object sender, EventArgs e)
        {
            xuly = 1;
            setControls(false);
            setControls2(true);
            setControls3(false);
            btnPrint.Enabled = false;
            txtFind.ReadOnly = true;
            
            strGioiTinh = myTable.Rows[row]["GioiTinh"] != DBNull.Value ? myTable.Rows[row]["GioiTinh"].ToString() : string.Empty;
            if (!string.IsNullOrEmpty(strGioiTinh))
            {
                if (string.Equals(strGioiTinh, "True", StringComparison.OrdinalIgnoreCase))
                {
                    radGTNam.Checked = true;
                }
                else
                {
                    radGTNu.Checked = true;
                }
            }

            // Kiểm tra và gán giá trị cho radio button trạng thái
            strTrangThai = myTable.Rows[row]["IsApproved"] != DBNull.Value ? myTable.Rows[row]["IsApproved"].ToString() : string.Empty;

            if (!string.IsNullOrEmpty(strTrangThai))
            {
                if (string.Equals(strTrangThai, "True", StringComparison.OrdinalIgnoreCase))
                {
                    ckbDangHD.Checked = true;
                    ckbNgungHD.Checked = false; // Uncheck the Ngừng hoạt động checkbox
                }
                else
                {
                    ckbDangHD.Checked = false; // Uncheck the Đang hoạt động checkbox
                    ckbNgungHD.Checked = true;
                }
            }

            // Đảm bảo chỉ một ô đánh dấu (checkbox)           
            ckbDangHD.CheckedChanged += (chkSender, chkE) =>
            {
                if (ckbDangHD.Checked && !isStatusConfirmed)
                {
                    if (ckbNgungHD.Checked) // Kiểm tra nếu đang chọn cả hai checkbox
                    {
                        DialogResult dialogResult = MessageBox.Show("Bạn có muốn thay đổi từ 'Ngừng hoạt động' sang 'Đang hoạt động'?", "Xác nhận thay đổi", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);

                        if (dialogResult == DialogResult.Yes)
                        {
                            ckbNgungHD.Checked = false; // Bỏ chọn checkbox Ngừng hoạt động
                            isStatusChanged = true; // Đánh dấu là đã thay đổi trạng thái
                            isStatusConfirmed = true; // Xác nhận đã xử lý thay đổi trạng thái
                        }
                        else
                        {
                            ckbDangHD.Checked = false;
                        }
                    }
                    else
                    {
                        isStatusChanged = true; // Đánh dấu là đã thay đổi trạng thái
                    }
                }
            };

            ckbNgungHD.CheckedChanged += (chkSender, chkE) =>
            {
                if (ckbNgungHD.Checked && !isStatusConfirmed)
                {
                    if (ckbDangHD.Checked) // Kiểm tra nếu đang chọn cả hai checkbox
                    {
                        DialogResult dialogResult = MessageBox.Show("Bạn có muốn thay đổi từ 'Đang hoạt động' sang 'Ngừng hoạt động'?", "Xác nhận thay đổi", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);

                        if (dialogResult == DialogResult.Yes)
                        {
                            ckbDangHD.Checked = false; // Bỏ chọn checkbox Đang hoạt động
                            isStatusChanged = true; // Đánh dấu là đã thay đổi trạng thái
                            isStatusConfirmed = true; // Xác nhận đã xử lý thay đổi trạng thái
                        }
                        else
                        {
                            ckbNgungHD.Checked = false;
                        }
                    }
                    else
                    {
                        isStatusChanged = true; // Đánh dấu là đã thay đổi trạng thái
                    }
                }
            };

            gridControlDSCCH.Enabled = false;
            txtTenCCH.Focus();
        }

        // Phương thức hoặc sự kiện khác trong lớp/Form
        private void OtherMethod()
        {
            if (isStatusChanged)
            {
                // Trạng thái đã thay đổi, thực hiện hành động tương ứng ở đây

                // Sau khi xử lý, reset biến isStatusChanged về giá trị mặc định để theo dõi các thay đổi tiếp theo
                isStatusChanged = false;
            }
        }

        // nút delete
        private void btnDelete_Click(object sender, EventArgs e)
        {
            DialogResult dlr = XtraMessageBox.Show("Bạn chắc chắn muốn xóa?", "Thông Báo", MessageBoxButtons.OKCancel, MessageBoxIcon.Question);
            if (dlr == DialogResult.OK)
            {
                try
                {
                    string xoadongsql = "DELETE FROM AspNetUsers WHERE Id='" + txtMaCH.Text + "'";
                    ketnoi(xoadongsql);
                    myCommand.ExecuteNonQuery();
                    XtraMessageBox.Show("Xóa thành công.", "Thông Báo");
                }
                catch (Exception)
                {
                    XtraMessageBox.Show("Xóa thất bại.", "Thông Báo");
                }
                string cauTruyVan = @"
                    SELECT U.*
                    FROM AspNetUsers U
                    INNER JOIN AspNetUserRoles UR ON U.Id = UR.UserId
                    INNER JOIN AspNetRoles R ON UR.RoleId = R.Id
                    WHERE R.Name = 'User'";

                gridControlDSCCH.DataSource = ketnoi(cauTruyVan);
                myConnection.Close();
                row = 0;
                GridView();
            }
        }

        // nút cancel
        private void btnCancel_Click(object sender, EventArgs e)
        {
            setControls(true);
            setControls2(false);
            txtMaCH.Text = strMaCH;
            txtTenCCH.Text = strTenCCH;
            txtSoDT.Text = strSoDT;
            dtmNgaySinh.Text = strNgaySinh;
            txtDiaChi.Text = strDiaChi;
            txtMoTa.Text = strMoTa;
            txtTenCH.Text = strTenCH;
            txtGioMoCua.Text = strEmail;
            picAnhCCH.Image = myImage;
            if (strGioiTinh == "True")
                radGTNam.Checked = true;
            else
                radGTNu.Checked = true;

            if (strTrangThai == "True")
                ckbDangHD.Checked = true;
            else
                ckbNgungHD.Checked = true;

            setControls3(true);
            btnPrint.Enabled = true;
            gridControlDSCCH.Enabled = true;
            txtFind.ReadOnly = false;
        }

        // nút print
        private void btnPrint_Click(object sender, EventArgs e)
        {
            frmInThe frmIn = new frmInThe(strTenCH);
            frmIn.Show();
        }

        // nút exit
        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private string iGioiTinh = "Khac";
        private string iTrangThai = "Khac";
        private bool bIsNumber;        
        private long lKTraSoDT;
        private string strDK = "Lỗi:";
    
        private void btnSave1_Click(object sender, EventArgs e)
        {
            if (txtTenCCH.Text == "")
                strDK += "\n    - Chưa nhập tên chủ cửa hàng.";

            // Kiểm tra xem email có hợp lệ hay không
            if (string.IsNullOrWhiteSpace(txtEmail.Text) || !IsValidEmail(txtEmail.Text))
                strDK += "\n    - Email không hợp lệ. Vui lòng nhập lại.";

            // Lưu giá trị của RadioButton giới tính
            iGioiTinh = radGTNam.Checked ? "True" : "False";

            // Lưu giá trị của RadioButton trạng thái
            iTrangThai = ckbDangHD.Checked ? "True" : "False";
            
            bIsNumber = long.TryParse(txtSoDT.Text, out lKTraSoDT);
            if (txtSoDT.Text == "")
            {
                strDK += "\n    - Chưa nhập số điện thoại.";
            }
            else if (bIsNumber == false || txtSoDT.Text.Length != 10)
            {
                strDK += "\n    - Số điện thoại không đúng.";
            }

            if (txtDiaChi.Text == "")
                strDK += "\n    - Chưa nhập địa chỉ.";

            if (strDK == "Lỗi:")
            {
                byte[] images = null;

                if (picAnhCCH.EditValue != null)
                {
                    using (MemoryStream stream = new MemoryStream())
                    {
                        picAnhCCH.Image.Save(stream, ImageFormat.Jpeg);
                        images = stream.ToArray();
                    }
                }
                else
                {
                    // Nếu không có ảnh được chọn, sử dụng ảnh mặc định
                    Image defaultImage = Image.FromFile("..\\cch\\user.png"); // Đường dẫn đến ảnh mặc định
                    using (MemoryStream stream = new MemoryStream())
                    {
                        defaultImage.Save(stream, ImageFormat.Jpeg);
                        images = stream.ToArray();
                    }
                }

                try
                {
                    string sqlCommandText = "";
                    if (xuly == 0)
                    {
                        sqlCommandText = "INSERT INTO AspNetUsers (Id, Fullname, Email, PhoneNumber, NgaySinh, GioiTinh, StoreName, Adress, Opentime, Closetime, MoTa, EmailConfirmed, PhoneNumberConfirmed, TwoFactorEnabled, LockoutEnabled, IsApproved, AccessFailedCount, Images) " +
                            "VALUES (@Id, @TenCCH, @Email, @SoDT, @NgaySinh, @GioiTinh, @TenCH, @DiaChi, @GioMoCua, @GioDongCua, @MoTa, @EmailConfirmed, @PhoneNumberConfirmed, @TwoFactorEnabled, @LockoutEnabled, @IsApproved, @AccessFailedCount, @Images)";
                    }
                    else if (xuly == 1)
                    {
                        sqlCommandText = "UPDATE AspNetUsers SET Fullname=@TenCCH, Email=@Email, GioiTinh=@GioiTinh, NgaySinh=@NgaySinh, PhoneNumber=@SoDT, StoreName=@TenCH, Adress=@DiaChi, Opentime=@GioMoCua, Closetime=@GioDongCua, MoTa=@MoTa, EmailConfirmed=@EmailConfirmed, PhoneNumberConfirmed=@PhoneNumberConfirmed, TwoFactorEnabled=@TwoFactorEnabled, LockoutEnabled=@LockoutEnabled, IsApproved=@IsApproved, AccessFailedCount=@AccessFailedCount, Images=@Images WHERE Id=@Id";
                    }

                    using (SqlConnection myConnection = new SqlConnection(strKetNoi))
                    {
                        myConnection.Open();

                        using (SqlCommand myCommand = new SqlCommand(sqlCommandText, myConnection))
                        {
                            myCommand.Parameters.AddWithValue("@Id", txtMaCH.Text);
                            myCommand.Parameters.AddWithValue("@TenCCH", string.IsNullOrEmpty(txtTenCCH.Text) ? (object)DBNull.Value : txtTenCCH.Text);
                            myCommand.Parameters.AddWithValue("@Email", string.IsNullOrEmpty(txtEmail.Text) ? (object)DBNull.Value : txtEmail.Text);
                            myCommand.Parameters.AddWithValue("@SoDT", string.IsNullOrEmpty(txtSoDT.Text) ? (object)DBNull.Value : txtSoDT.Text);
                            myCommand.Parameters.AddWithValue("@NgaySinh", dtmNgaySinh.Value); // Sửa để tránh lỗi, bạn cần thêm ngày tháng năm cụ thể ở đây
                            myCommand.Parameters.AddWithValue("@GioiTinh", iGioiTinh);
                            myCommand.Parameters.AddWithValue("@TenCH", string.IsNullOrEmpty(txtTenCH.Text) ? (object)DBNull.Value : txtTenCH.Text);
                            myCommand.Parameters.AddWithValue("@DiaChi", string.IsNullOrEmpty(txtDiaChi.Text) ? (object)DBNull.Value : txtDiaChi.Text);
                            myCommand.Parameters.AddWithValue("@GioMoCua", string.IsNullOrEmpty(txtGioMoCua.Text) ? (object)DBNull.Value : txtGioMoCua.Text);
                            myCommand.Parameters.AddWithValue("@GioDongCua", string.IsNullOrEmpty(txtGioDongCua.Text) ? (object)DBNull.Value : txtGioDongCua.Text);
                            myCommand.Parameters.AddWithValue("@MoTa", string.IsNullOrEmpty(txtMoTa.Text) ? (object)DBNull.Value : txtMoTa.Text);
                            myCommand.Parameters.AddWithValue("@EmailConfirmed", false); // EmailConfirmed có giá trị false
                            myCommand.Parameters.AddWithValue("@PhoneNumberConfirmed", false); // PhoneNumberConfirmed có giá trị false
                            myCommand.Parameters.AddWithValue("@TwoFactorEnabled", false); // TwoFactorEnabled có giá trị false
                            myCommand.Parameters.AddWithValue("@LockoutEnabled", true); // LockoutEnabled có giá trị true
                            myCommand.Parameters.AddWithValue("@IsApproved", iTrangThai); // Cập nhật trạng thái IsApproved dựa vào iTrangThai
                            myCommand.Parameters.AddWithValue("@AccessFailedCount", 0); // AccessFailedCount có giá trị là 0
                            myCommand.Parameters.AddWithValue("@Images", images);

                            int rowsAffected = myCommand.ExecuteNonQuery();
                            if (rowsAffected > 0)
                            {
                                if (xuly == 0)
                                    XtraMessageBox.Show("Thêm thành công.", "Thông Báo");
                                else
                                    XtraMessageBox.Show("Sửa thành công.", "Thông Báo");

                                string cauTruyVan = @"
                                    SELECT U.*
                                    FROM AspNetUsers U
                                    INNER JOIN AspNetUserRoles UR ON U.Id = UR.UserId
                                    INNER JOIN AspNetRoles R ON UR.RoleId = R.Id
                                    WHERE R.Name = 'User'";
                                gridControlDSCCH.DataSource = ketnoi(cauTruyVan);
                            }
                            else
                            {
                                XtraMessageBox.Show("Thao tác không thành công.", "Thông Báo");
                            }
                        }
                    }

                    txtFind.ReadOnly = false;
                    setControls3(true);
                    btnPrint.Enabled = true;
                    setControls(true);
                    setControls2(false);
                    row = 0;
                    GridView();
                    gridControlDSCCH.Enabled = true;
                }
                catch (Exception ex)
                {
                    XtraMessageBox.Show("Lỗi: " + ex.Message, "Lỗi");
                }
            }
            else
            {
                strDK += "\nVui lòng nhập đầy đủ thông tin.";
                XtraMessageBox.Show(strDK, "Thông Báo");
                if (txtTenCCH.Text == "")
                    txtTenCCH.Focus();
                else if (txtSoDT.Text == "" || txtSoDT.Text.Length != 10)
                    txtSoDT.Focus();
                else if (txtDiaChi.Text == "")
                {
                    txtDiaChi.Focus();
                }
            }

            strDK = "Lỗi:";
        }

        // tìm kiếm
        private void txtFind_TextChanged(object sender, EventArgs e)
        {
            string timkiem = @"
                SELECT U.*
                FROM AspNetUsers U
                INNER JOIN AspNetUserRoles UR ON U.Id = UR.UserId
                INNER JOIN AspNetRoles R ON UR.RoleId = R.Id
                WHERE R.Name = 'User'";

            if (radTenCH.Checked)
            {
                if (!string.IsNullOrEmpty(txtFind.Text))
                {
                    timkiem += " AND U.StoreName LIKE N'%" + txtFind.Text + "%'";
                }
            }
            else if (radTenCCH.Checked)
            {
                if (!string.IsNullOrEmpty(txtFind.Text))
                {
                    timkiem += " AND U.Fullname LIKE N'%" + txtFind.Text + "%'";
                }
            }

            ketnoi(timkiem);
            myCommand.ExecuteNonQuery();
            gridControlDSCCH.DataSource = ketnoi(timkiem);
            myConnection.Close();
        }

        private void radTenCH_Click(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }

        private void radTenCCH_Click(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }
        
        private void radTenCH_CheckedChanged(object sender, EventArgs e)
        {
            if (radTenCH.Checked)
            {
                string timkiem = @"
                    SELECT U.*
                    FROM AspNetUsers U
                    INNER JOIN AspNetUserRoles UR ON U.Id = UR.UserId
                    INNER JOIN AspNetRoles R ON UR.RoleId = R.Id
                    WHERE R.Name = 'User' AND U.StoreName LIKE N'%" + txtFind.Text + "%'";
                // Thực hiện truy vấn và hiển thị dữ liệu
                gridControlDSCCH.DataSource = ketnoi(timkiem);
                // Đóng kết nối sau khi sử dụng
                myConnection.Close();
            }
        }

        private void radTenCCH_CheckedChanged(object sender, EventArgs e)
        {
            if (radTenCCH.Checked)
            {
                string timkiem = @"
                    SELECT U.*
                    FROM AspNetUsers U
                    INNER JOIN AspNetUserRoles UR ON U.Id = UR.UserId
                    INNER JOIN AspNetRoles R ON UR.RoleId = R.Id
                    WHERE R.Name = 'User' AND U.Fullname LIKE N'%" + txtFind.Text + "%'";
                // Thực hiện truy vấn và hiển thị dữ liệu
                gridControlDSCCH.DataSource = ketnoi(timkiem);
                // Đóng kết nối sau khi sử dụng
                myConnection.Close();
            }
        }

        private void chupCamera()
        {
            DevExpress.XtraEditors.Camera.TakePictureDialog dialog = new DevExpress.XtraEditors.Camera.TakePictureDialog();
            if (dialog.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                System.Drawing.Image ima = dialog.Image;
                using (var stream = new MemoryStream())
                {
                    ima.Save(stream, ImageFormat.Jpeg);
                    picAnhCCH.EditValue = stream.ToArray();
                }
            }
        }

        private void btnCamera_Click(object sender, EventArgs e)
        {
            chupCamera();
        }
        
        // Hàm kiểm tra định dạng email
        private bool IsValidEmail(string email)
        {
            try
            {
                var addr = new System.Net.Mail.MailAddress(email);
                return addr.Address == email;
            }
            catch
            {
                return false;
            }
        }
    }
}