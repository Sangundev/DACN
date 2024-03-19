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
using System.Drawing.Imaging;

namespace LTWin_PMQLTV
{
    public partial class frmQLSP : DevExpress.XtraEditors.XtraForm
    {
        public frmQLSP()
        {
            InitializeComponent();
        }

        // Khai báo kết nối
        private string strKetNoi = ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
        private SqlConnection myConnection; // kết nối tới csdl
        private SqlDataAdapter myDataAdapter;   // Vận chuyển csdl qa DataSet
        private DataTable myTable;  // Dùng để lưu bảng lấy từ c#
        private SqlCommand myCommand;   // Thực hiện cách lệnh truy vấn

        // khai báo kết nối tới bảng
        //private DataTable myTableNCC;

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

        // Kết nối tới tblDocGia
        //private DataTable ketnoiNCC(string truyvan)
        //{
        //    myConnection = new SqlConnection(strKetNoi);
        //    myConnection.Open();
        //    string thuchiencaulenh = truyvan;
        //    myCommand = new SqlCommand(thuchiencaulenh, myConnection);
        //    myDataAdapter = new SqlDataAdapter(myCommand);
        //    myTableNCC = new DataTable();
        //    myDataAdapter.Fill(myTableNCC);
        //    return myTableNCC;
        //}

        // lấy Mã DG lên cboMaDG
        //public void layMaDGPM()
        //{
        //    string strLayMaDG = "select * from Product";
        //    //cboNCC.DataSource = ketnoiNCC(strLayMaDG);
        //    //cboNCC.DisplayMember = "TENNCC";
        //    //cboNCC.ValueMember = "TENNCC";
        //    myConnection.Close();
        //}

        
        

        // thiết lập ẩn hiện controls
        private void setControls(bool edit)
        {
            txtTenSP.ReadOnly = edit;
            //txtGhiChu.ReadOnly = edit;
            //txtDonGia.ReadOnly = edit;
            txtSoLuong.ReadOnly = edit;
            //txtGhiChu.ReadOnly = edit;
            txtGia.ReadOnly = edit;
            picAnhSach.ReadOnly = edit;

        }

        // thiết lập ẩn hiện controls
        private void setControls2(bool edit)
        {
            cboDanhmuc.Enabled = edit;
            //cboNCC.Enabled = edit;
            //cboNXB.Enabled = edit;
            cboTinhTrang.Enabled = edit;

            btnCamera.Enabled = edit;
            btnLoadImg.Enabled = edit;
            btnSave.Enabled = edit;
            btnCancel.Enabled = edit;

        }

        // Thiết lập controls
        private void setControls3(bool edit)
        {
            btnRefresh.Enabled = edit;
            btnAdd.Enabled = edit;
            btnEdit.Enabled = edit;
            btnDelete.Enabled = edit;
        }

        // Chuyển byte thành ảnh
        public Image ByteArrayToImage(byte[] byteArrayIn)
        {
            MemoryStream ms = new MemoryStream(byteArrayIn);
            Image returnImage = Image.FromStream(ms);
            return returnImage;
        }

        public Image myImage = null;

        // Load dữ liệu từ gridControls lên controls
        public int row;
        public string strMaSP, imgs, strTenSP, strDanhMuc, strGia, strSoLuong, strTinhTrang;
        private void GridView()
        {
            txtMaSP.Text = myTable.Rows[row]["Productid"].ToString();
            strMaSP = txtMaSP.Text;
            txtTenSP.Text = myTable.Rows[row]["Productname"].ToString();
            strTenSP = txtTenSP.Text;
            cboDanhmuc.Text = myTable.Rows[row]["Categoryid"].ToString();
            strDanhMuc = cboDanhmuc.Text;
            txtGia.Text = myTable.Rows[row]["price"].ToString();
            strGia = txtGia.Text;
            //cboNXB.Text = myTable.Rows[row][4].ToString();
            //strNXB = cboNXB.Text;
            txtSoLuong.Text = myTable.Rows[row]["Soluong"].ToString();
            strSoLuong = txtSoLuong.Text;
            //txtDonGia.Text = myTable.Rows[row][6].ToString();
            //strDonGia = txtDonGia.Text;
            cboTinhTrang.Text = myTable.Rows[row]["status"].ToString();
            strTinhTrang = cboTinhTrang.Text;
            //cboNCC.Text = myTable.Rows[row][8].ToString();
            //strNCC = cboNCC.Text;
            //txtGhiChu.Text = myTable.Rows[row][9].ToString();
            //strGhiChu = txtGhiChu.Text;
            //imgs = myTable.Rows[row]["image"].ToString();
            //if (imgs == "")
            //{
            //    myImage = Image.FromFile("..\\cch\\user.png");
            //}
            //else
            //{
            //    myImage = ByteArrayToImage((byte[])myTable.Rows[row]["image"]);
            //}
            //picAnhSach.Image = myImage;
        }

        //private DataTable myTableHSMuonTra;
        //// Kết nối tới tblHSMuonTra
        //private DataTable ketnoitblCTMuonTra(string truyvan)
        //{
        //    myConnection = new SqlConnection(strKetNoi);
        //    myConnection.Open();
        //    string thuchiencaulenh = truyvan;
        //    myCommand = new SqlCommand(thuchiencaulenh, myConnection);
        //    myDataAdapter = new SqlDataAdapter(myCommand);
        //    myTableHSMuonTra = new DataTable();
        //    myDataAdapter.Fill(myTableHSMuonTra);
        //    return myTableHSMuonTra;
        //}

        //// lấy Mã DG lên cboMaDG
        //public void layMaDGPM1()
        //{
        //    string strLayMaDG = "select TENNCC from NCC";
        //    cboNCC.DataSource = ketnoitblCTMuonTra(strLayMaDG);
        //    cboNCC.DisplayMember = "TENNCC";
        //    cboNCC.ValueMember = "TENNCC";
        //    myConnection.Close();
        //}

        // sư kiện load form
        private void frmQLSach_Load(object sender, EventArgs e)
        {
            string cauTruyVan = "select * from Product";
            gridControlDSSach.DataSource = ketnoi(cauTruyVan);
            myConnection.Close();
            txtMaSP.ReadOnly = true;
            setControls(true);
            setControls2(false);

            row = 0;
            GridView();
            txtFind.Focus();
            txtMaSP.ReadOnly = true;
            radMaSP.Checked = true;
        }

        // sự kiện khi row của gridControls được chọn
        private void gridViewDSSach_RowClick(object sender, DevExpress.XtraGrid.Views.Grid.RowClickEventArgs e)
        {
            row = e.RowHandle;
            GridView();
        }

        // Lấy ảnh lên picturebox
        string imgLocation = "";
        private void btnLoadImg_Click(object sender, EventArgs e)
        {
            OpenFileDialog dlrOpen = new OpenFileDialog();
            dlrOpen.Filter = "All file(*.*)|*.*|Png files(*.png)|*.png|Jpg files(*.jpg)|*.jpg";
            if (dlrOpen.ShowDialog() == DialogResult.OK)
            {
                imgLocation = dlrOpen.FileName.ToString();
                Image img = Image.FromFile(imgLocation);
                picAnhSach.Image = img;
                //txtImg.Text = imgLocation;
            }
        }

        private byte[] images = null;
        private void chupCamera()
        {
            DevExpress.XtraEditors.Camera.TakePictureDialog dialog = new DevExpress.XtraEditors.Camera.TakePictureDialog();
            if (dialog.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                System.Drawing.Image ima = dialog.Image;
                using (var stream = new MemoryStream())
                {
                    ima.Save(stream, ImageFormat.Jpeg);
                    picAnhSach.EditValue = stream.ToArray();
                }
            }
        }

        // chụp ảnh với camera
        private void btnCamera_Click(object sender, EventArgs e)
        {
            chupCamera();
        }

        // Nút refresh
        private void btnRefresh_Click(object sender, EventArgs e)
        {
            string cauTruyVan = "select * from Product";
            gridControlDSSach.DataSource = ketnoi(cauTruyVan);
            myConnection.Close();
            row = 0;
            GridView();
        }

        // nút add
        public int xuly = 2;
        private void btnAdd_Click(object sender, EventArgs e)
        {
            //layMaDGPM();
            //layMaDGPM1();
            xuly = 0;
            setControls(false);
            setControls2(true);
            setControls3(false);
            txtFind.ReadOnly = true;

            txtMaSP.Text = Guid.NewGuid().ToString();
            txtTenSP.Text = "";
            cboDanhmuc.Text = "";
            txtGia.Text = "";
            //cboNXB.Text = "";
            txtSoLuong.Text = "";
            //txtDonGia.Text = "";
            //txtGhiChu.Text = "";
            //cboNCC.Text = "";
            cboTinhTrang.Text = "";
            txtTenSP.Focus();
            //////////////////////////////////////////
            imgLocation = "..\\cch\\user.png";
            images = null;
            FileStream Streem = new FileStream(imgLocation, FileMode.Open, FileAccess.Read);
            BinaryReader brs = new BinaryReader(Streem);
            images = brs.ReadBytes((int)Streem.Length);

            gridControlDSSach.Enabled = false;

            cboDanhmuc.DropDownStyle = ComboBoxStyle.DropDown;
            //cboNCC.DropDownStyle = ComboBoxStyle.DropDownList;
            cboTinhTrang.DropDownStyle = ComboBoxStyle.DropDown;
            //cboNXB.DropDownStyle = ComboBoxStyle.DropDown;
        }

        // nút edit
        private void btnEdit_Click(object sender, EventArgs e)
        {
            xuly = 1;
            //layMaDGPM();
            setControls(false);
            setControls2(true);
            setControls3(false);
            txtFind.ReadOnly = true;
            gridControlDSSach.Enabled = false;
            txtTenSP.Focus();
        }

        // nut delete
        private void btbDelete_Click(object sender, EventArgs e)
        {
            DialogResult dlr = XtraMessageBox.Show("Bạn chắc chắn muốn xóa?", "Thông Báo", MessageBoxButtons.OKCancel, MessageBoxIcon.Question);
            if (dlr == DialogResult.OK)
            {
                try
                {
                    string xoadongsql = "delete from Prodcut where Productid='" + txtMaSP.Text + "'";
                    ketnoi(xoadongsql);
                    myCommand.ExecuteNonQuery();
                    XtraMessageBox.Show("Xóa thành công.", "Thông Báo");
                    //btnXoa.Enabled = false;
                }
                catch (Exception)
                {
                    XtraMessageBox.Show("Xóa thất bại.\nSách này đang được mượn.", "Thông Báo");
                }
                string cauTruyVan = "select * from SACH";
                gridControlDSSach.DataSource = ketnoi(cauTruyVan);
                myConnection.Close();
                row = 0;
                GridView();
            }
        }

        // nút lưu
        public bool bDonGia, bSoLuong;
        public long lKTraDonGia, lKtraSoLuong;
        private string strDK = "Lỗi:";  // lưu lỗi nhập liệu
        private void btnSave_Click(object sender, EventArgs e)
        {
            //// kiểm tra nhập liệu
            //if (txtTenSP.Text == "")
            //    strDK += "\n    - Chưa nhập tên sách.";

            //if (txtGia.Text == "")
            //    strDK += "\n    - Chưa nhập tên tác giả.";

            //bSoLuong = long.TryParse(txtSoLuong.Text, out lKtraSoLuong);
            //if (txtSoLuong.Text == "")
            //{
            //    strDK += "\n    - Chưa nhập số lượng.";
            //}
            //else if (bSoLuong == false)
            //{
            //    strDK += "\n    - Số lượng không đúng.";
            //}

            ////bDonGia = long.TryParse(txtDonGia.Text, out lKTraDonGia);
            ////if (txtDonGia.Text == "")
            ////{
            ////    strDK += "\n    - Chưa nhập đơn giá.";
            ////}
            ////else if (bDonGia == false)
            ////{
            ////    strDK += "\n    - Đơn giá không đúng.";
            ////}

            //if (strDK == "Lỗi:")
            //{
            //    if (xuly == 0)
            //    {
            //        //byte[] images = null;
            //        if (picAnhSach.EditValue != null)
            //        {
            //            MemoryStream stream = new MemoryStream();
            //            picAnhSach.Image.Save(stream, ImageFormat.Jpeg);
            //            images = stream.ToArray();
            //        }
            //        try
            //        {
            //            string themdongsql = "insert into SACH(MASACH, TENSACH, CHUDE, TACGIA, NXB, SLNHAP, DONGIA, TINHTRANG, NCC, GHICHU, HINHANH) values ('" + txtMaSP.Text + "',N'" + txtTenSP.Text + "',N'" + cboDanhmuc.Text + "',N'" + txtGia.Text + "','" + txtSoLuong.Text + "',N'" + cboTinhTrang.Text + "',@images)";
            //            myConnection = new SqlConnection(strKetNoi);
            //            myConnection.Open();
            //            myCommand = new SqlCommand(themdongsql, myConnection);
            //            myCommand.Parameters.Add(new SqlParameter("@images", images));
            //            myCommand.ExecuteNonQuery();
            //            //ketnoi(themdongsql);
            //            XtraMessageBox.Show("Thêm thành công.", "Thông Báo");

            //            string cauTruyVan = "select * from SACH";
            //            gridControlDSSach.DataSource = ketnoi(cauTruyVan);
            //            myConnection.Close();
            //            txtFind.ReadOnly = false;

            //            setControls(true);
            //            setControls2(false);
            //            setControls3(true);
            //            row = 0;
            //            GridView();
            //            gridControlDSSach.Enabled = true;
            //            myCommand.ExecuteNonQuery();
            //        }
            //        catch (Exception)
            //        {

            //        }
            //    }
            //    else if (xuly == 1)
            //    {
            //        try
            //        {

            //            if (picAnhSach.EditValue != null)
            //            {
            //                MemoryStream stream = new MemoryStream();
            //                picAnhSach.Image.Save(stream, ImageFormat.Jpeg);
            //                images = stream.ToArray();
            //            }

            //            string capnhatdongsql = "update SACH set TENSACH=N'" + txtTenSP.Text + "',CHUDE=N'" + cboDanhmuc.Text + "',TACGIA=N'" + txtGia.Text + "',SLNHAP='" + txtSoLuong.Text + "',TINHTRANG=N'" + cboTinhTrang.Text + "',HINHANH=@images where MASACH='" + txtMaSP.Text + "'";
            //            myConnection = new SqlConnection(strKetNoi);
            //            myConnection.Open();
            //            myCommand = new SqlCommand(capnhatdongsql, myConnection);
            //            myCommand.Parameters.Add(new SqlParameter("@images", images));
            //            myCommand.ExecuteNonQuery();
            //            //ketnoi(capnhatdongsql);
            //            //myCommand.ExecuteNonQuery();
            //            XtraMessageBox.Show("Sửa thành công.", "Thông Báo");

            //            string cauTruyVan = "select * from SACH";
            //            gridControlDSSach.DataSource = ketnoi(cauTruyVan);
            //            myConnection.Close();

            //            txtFind.ReadOnly = false;
            //            setControls3(true);
            //            setControls(true);
            //            setControls2(false);
            //            row = 0;
            //            GridView();
            //            gridControlDSSach.Enabled = true;
            //        }
            //        catch
            //        {
            //            XtraMessageBox.Show("Sửa thất bại.\nVui lòng kiểm tra lại dữ liệu.", "Thông Báo");
            //        }
            //    }
            //}
            //else
            //{
            //    strDK += "\nVui lòng nhập đầy đủ thông tin.";
            //    XtraMessageBox.Show(strDK, "Thông Báo");
            //    if (txtTenSP.Text == "")
            //        txtTenSP.Focus();
            //    else if (txtGia.Text == "")
            //        txtGia.Focus();
            //    else if (bSoLuong == false)
            //        txtSoLuong.Focus();
            //    //else if (bDonGia == false)
            //    //{
            //    //    //txtDonGia.Focus();
            //    //}
            //}
            //strDK = "Lỗi:";
        }

        // nút cancel
        private void btnCancel_Click(object sender, EventArgs e)
        {
            setControls(true);
            setControls2(false);
            setControls3(true);
            txtMaSP.Text = strMaSP;
            txtTenSP.Text = strTenSP;
            cboDanhmuc.Text = strDanhMuc;
            txtGia.Text = strGia;
            //cboNXB.Text = strNXB;
            txtSoLuong.Text = strSoLuong;
            //txtDonGia.Text = strDonGia;
            //txtGhiChu.Text = strGhiChu;
            //cboNCC.Text = strNCC;
            cboTinhTrang.Text = strTinhTrang;

            gridControlDSSach.Enabled = true;
            txtFind.ReadOnly = false;
        }

        // nút exit
        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        // tìm kiếm
        private void txtFind_TextChanged(object sender, EventArgs e)
        {
            if (radMaSP.Checked)
            {
                string timkiem = "select * from Product where Productid like '%" + txtFind.Text + "%'";
                ketnoi(timkiem);
                myCommand.ExecuteNonQuery();
                gridControlDSSach.DataSource = ketnoi(timkiem);
                myConnection.Close();
            }
            else if (radGia.Checked)
            {
                string timkiem = "select * from Product where price like N'%" + txtFind.Text + "%'";
                ketnoi(timkiem);
                myCommand.ExecuteNonQuery();
                gridControlDSSach.DataSource = ketnoi(timkiem);
                myConnection.Close();
            }
            else if (radTenSP.Checked)
            {
                string timkiem = "select * from Product where Productname like N'%" + txtFind.Text + "%'";
                ketnoi(timkiem);
                myCommand.ExecuteNonQuery();
                gridControlDSSach.DataSource = ketnoi(timkiem);
                myConnection.Close();
            }
            else if (radTinhTrang.Checked)
            {
                string timkiem = "select * from Product where Categoryid like N'%" + txtFind.Text + "%'";
                ketnoi(timkiem);
                myCommand.ExecuteNonQuery();
                gridControlDSSach.DataSource = ketnoi(timkiem);
                myConnection.Close();
            }
        }

        private void radMaSach_CheckedChanged(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }

        private void radChuDe_CheckedChanged(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }

        private void radTenSach_CheckedChanged(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }

        private void radTacGia_CheckedChanged(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }
    }
}