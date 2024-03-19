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
    public partial class frmCH : DevExpress.XtraEditors.XtraForm
    {
        public frmCH()
        {
            InitializeComponent();
        }

        // Khai báo kết nối
        private string strKetNoi = ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
        private SqlConnection myConnection; // kết nối tới csdl
        private SqlDataAdapter myDataAdapter;   // vận chuyển csdl qua dataset
        private DataTable myTable;  // dùng để lưu bảng lấy từ c#
        SqlCommand myCommand;   // thực hiện cách lệnh truy vấn

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

        // tăng mã tự động
        public string tangMaTuDong()
        {
            string cauTruyVan = "select * from CH";
            ketnoi(cauTruyVan);
            myConnection.Close();
            string maTuDong = "";
            if (myTable.Rows.Count <= 0)
            {
                maTuDong = "CH001";
            }
            else
            {
                int k;
                maTuDong = "CH";
                k = Convert.ToInt32(myTable.Rows[myTable.Rows.Count - 1][0].ToString().Substring(3, 3));
                k = k + 1;
                if (k < 10)
                {
                    maTuDong = maTuDong + "00";
                }
                else if (k < 100)
                {
                    maTuDong = maTuDong + "0";
                }
                maTuDong = maTuDong + k.ToString();
            }
            return maTuDong;
        }

        // thiết lập ẩn hiện
        private void setControls(bool edit)
        {
            txtTenCH.ReadOnly = edit;
            txtSoDT.ReadOnly = edit;
            txtDiaChi.ReadOnly = edit;
        }

        // thiết lập ẩn hiện
        private void setControls2(bool edit)
        {
            btnSave.Enabled = edit;
            btnCancel.Enabled = edit;
        }

        // thiết lập ẩn hiện
        private void setControls3(bool edit)
        {
            btnRefresh.Enabled = edit;
            btnAdd.Enabled = edit;
            btnEdit.Enabled = edit;
            btnDelete.Enabled = edit;
        }

        // show dữ liệu từ gridview lên controls
        public int row; // chỉ số dòng trên gridview
        public string strMaCH, strTenCH, strSoDT, strDiaChi; // save giá trị của controls
        private void GridView()
        {
            txtMaCH.Text = myTable.Rows[row]["MaCH"].ToString();
            strMaCH = txtMaCH.Text;
            txtTenCH.Text = myTable.Rows[row]["TenCH"].ToString();
            strTenCH = txtTenCH.Text;
            txtSoDT.Text = myTable.Rows[row]["SDT"].ToString();
            strSoDT = txtSoDT.Text;
            txtDiaChi.Text = myTable.Rows[row]["DiaChi"].ToString();
            strDiaChi = txtDiaChi.Text;
        }

        // sự kiện load form
        private void frmSupplier_Load(object sender, EventArgs e)
        {
            string cauTruyVan = "select * from CH";
            gridControlDSCH.DataSource = ketnoi(cauTruyVan);
            myConnection.Close();
            txtMaCH.ReadOnly = true;

            setControls(true);
            setControls2(false);
            row = 0;
            GridView();
        }

        // sự kiện khi row được click
        private void gridViewDSNCC_RowClick(object sender, DevExpress.XtraGrid.Views.Grid.RowClickEventArgs e)
        {
            row = e.RowHandle;
            GridView();
        }

        // btnRefresh
        private void btnRefresh_Click(object sender, EventArgs e)
        {
            string cauTruyVan = "select * from CH";
            gridControlDSCH.DataSource = ketnoi(cauTruyVan);
            myConnection.Close();
            row = 0;
            GridView();
        }

        // btnAdd
        public int xuly = 2;    // biến xác nhận add or edit
        private void btnAdd_Click(object sender, EventArgs e)
        {
            xuly = 0;
            txtMaCH.Text = Guid.NewGuid().ToString();
            txtTenCH.Text = "";
            txtSoDT.Text = "";
            txtDiaChi.Text = "";
            txtTenCH.Focus();

            setControls(false);
            setControls2(true);
            setControls3(false);
            gridControlDSCH.Enabled = false; 
        }

        // btnEdit
        private void btnEdit_Click(object sender, EventArgs e)
        {
            xuly = 1;
            setControls(false);
            setControls2(true);
            setControls3(false);
            gridControlDSCH.Enabled = false;
            txtTenCH.Focus();
        }

        // btnDelete
        private void btnDelete_Click(object sender, EventArgs e)
        {
            DialogResult dlr = XtraMessageBox.Show("Bạn chắc chắn muốn xóa?", "Thông Báo", MessageBoxButtons.OKCancel, MessageBoxIcon.Question);
            if (dlr == DialogResult.OK)
            {
                try
                {
                    string xoadongsql = "delete from CH where MaCH='" + txtMaCH.Text + "'";
                    ketnoi(xoadongsql);
                    myCommand.ExecuteNonQuery();
                    XtraMessageBox.Show("Xóa thành công.", "Thông Báo");
                }
                catch (Exception)
                {
                    XtraMessageBox.Show("Xóa thất bại.", "Thông Báo");
                }
                string cauTruyVan = "select * from CH";
                gridControlDSCH.DataSource = ketnoi(cauTruyVan);
                myConnection.Close();
                row = 0;
                GridView();
            }
        }

        // btnSave
        public bool bIsNumber;  // kiểm tra số ĐT
        public long lKTraSoDT;  // lưu số ĐT
        public string strDK = "Lỗi:";
        private void btnSave_Click(object sender, EventArgs e)
        {
            if (txtTenCH.Text == "")
                strDK += "\n    - Chưa nhập tên cửa hàng.";

            bIsNumber = long.TryParse(txtSoDT.Text, out lKTraSoDT);
            if (txtSoDT.Text == "")
            {
                strDK += "\n    - Chưa nhập số điện thoại.";
            }
            else if (bIsNumber == false || txtSoDT.Text.Length < 9)
            {
                strDK += "\n    - Số điện thoại không đúng.";
            }

            if (txtDiaChi.Text == "")
                strDK += "\n    - Chưa nhập địa chỉ.";

            if (strDK == "Lỗi:")
            {
                if (xuly == 0)
                {
                    try
                    {
                        string themdongsql = "insert into CH values ('" + txtMaCH.Text + "',N'" + txtTenCH.Text + "','" + txtSoDT.Text + "',N'" + txtDiaChi.Text + "')";
                        ketnoi(themdongsql);
                        XtraMessageBox.Show("Thêm thành công.", "Thông Báo");

                        string cauTruyVan = "select * from CH";
                        gridControlDSCH.DataSource = ketnoi(cauTruyVan);
                        myConnection.Close();

                        row = 0;
                        GridView();
                        setControls(true);
                        setControls2(false);
                        setControls3(true);
                        gridControlDSCH.Enabled = true;
                        myCommand.ExecuteNonQuery();
                    }
                    catch (Exception)
                    {

                    }
                }
                else if (xuly == 1)
                {
                    try
                    {
                        string capnhatdongsql = "update CH set TenCH=N'" + txtTenCH.Text + "',SDT='" + txtSoDT.Text + "',DiaChi=N'" + txtDiaChi.Text + "' where MaCH='" + txtMaCH.Text + "'";
                        ketnoi(capnhatdongsql);
                        myCommand.ExecuteNonQuery();
                        XtraMessageBox.Show("Sửa thành công.", "Thông Báo");

                        string cauTruyVan = "select * from CH";
                        gridControlDSCH.DataSource = ketnoi(cauTruyVan);
                        myConnection.Close();

                        row = 0;
                        GridView();
                        setControls(true);
                        setControls2(false);
                        setControls3(true);
                        gridControlDSCH.Enabled = true;
                    }
                    catch
                    {
                        XtraMessageBox.Show("Sửa thất bại.\nVui lòng kiểm tra lại dữ liệu.", "Thông Báo");
                    }
                }
            }
            else
            {
                strDK += "\nVui lòng nhập đầy đủ thông tin.";
                XtraMessageBox.Show(strDK, "Thông Báo");
                if (txtTenCH.Text == "")
                    txtTenCH.Focus();
                else if (txtSoDT.Text == "" || txtSoDT.Text.Length < 9)
                    txtSoDT.Focus();
                else if (txtDiaChi.Text == "")
                {
                    txtDiaChi.Focus();
                }
            }
            strDK = "Lỗi:";
        }

        // btnCancel
        private void btnCancel_Click(object sender, EventArgs e)
        {
            txtMaCH.Text = strMaCH;
            txtTenCH.Text = strTenCH;
            txtSoDT.Text = strSoDT;
            txtDiaChi.Text = strDiaChi;

            setControls(true);
            setControls2(false);
            setControls3(true);
            gridControlDSCH.Enabled = true;
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}