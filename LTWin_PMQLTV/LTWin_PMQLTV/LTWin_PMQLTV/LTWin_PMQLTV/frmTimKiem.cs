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
    public partial class frmTimKiem : DevExpress.XtraEditors.XtraForm
    {
        public frmTimKiem()
        {
            InitializeComponent();
        }

        // Khai báo kết nối
        string strKetNoi = ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
        private SqlConnection myConnection; // kết nối tới csdl
        private SqlDataAdapter myDataAdapter;   // Vận chuyển csdl qa DataSet
        private DataTable myTable;  // Dùng để lưu bảng lấy từ c#
        SqlCommand myCommand;   // Thực hiện cách lệnh truy vấn

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
            //gridControlDSDocGia.DataSource = myTable;
            return myTable;
        }

        private void frmSearch_Load(object sender, EventArgs e)
        {
            radMaCH.Checked = true;
        }

        private void radMaCH_Click(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }

        //private void radMaCH_CheckedChanged(object sender, EventArgs e)
        //{
        //    if (radMaCH.Checked)
        //    {
        //        string timkiem = "select * from AspNetUsers where Id like '%" + txtFind.Text + "%'";
        //        // Thực hiện truy vấn và hiển thị dữ liệu
        //        gridControlKQ.DataSource = ketnoi(timkiem);
        //        // Đóng kết nối sau khi sử dụng
        //        myConnection.Close();
        //    }
        //}

        private void radTenCCH_CheckedChanged(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }

        private void radTenCH_Click(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }

        private void radDiaChi_CheckedChanged(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }

        private void txtFind_TextChanged(object sender, EventArgs e)
        {
            if (radMaCH.Checked)
            {
                gridControlKQ.Visible = true;
                gridControlKQDG.Visible = false;
                string timkiem = "select * from AspNetUsers where Id like '%" + txtFind.Text + "%'";
                //ketnoi(timkiem);
                //myCommand.ExecuteNonQuery();
                gridControlKQ.DataSource = ketnoi(timkiem);
                myConnection.Close();
            }
            else if (radTenCH.Checked)
            {
                gridControlKQ.Visible = true;
                gridControlKQDG.Visible = false;
                string timkiem = "select * from AspNetUsers where StoreName like N'%" + txtFind.Text + "%'";
                //ketnoi(timkiem);
                //myCommand.ExecuteNonQuery();
                gridControlKQ.DataSource = ketnoi(timkiem);
                myConnection.Close();
            }
            else if (radTenCCH.Checked)
            {
                gridControlKQ.Visible = true;
                gridControlKQDG.Visible = false;
                string timkiem = "select * from AspNetUsers where Fullname like N'%" + txtFind.Text + "%'";
                //ketnoi(timkiem);
                //myCommand.ExecuteNonQuery();
                gridControlKQ.DataSource = ketnoi(timkiem);
                myConnection.Close();
            }
            else if (radDiaChi.Checked)
            {
                gridControlKQ.Visible = true;
                gridControlKQDG.Visible = false;
                string timkiem = "select * from AspNetUsers where Adress like N'%" + txtFind.Text + "%'";
                //ketnoi(timkiem);
                //myCommand.ExecuteNonQuery();
                gridControlKQ.DataSource = ketnoi(timkiem);
                myConnection.Close();
            }
            else if (radTenKH.Checked)
            {
                gridControlKQ.Visible = false;
                gridControlKQDG.Visible = true;
                string timkiem = "select * from AspNetUsers where UserName like '%" + txtFind.Text + "%'";
                //ketnoi(timkiem);
                //myCommand.ExecuteNonQuery();               
                gridControlKQDG.DataSource = ketnoi(timkiem);
                myConnection.Close();
            }
            else if (radSDT.Checked)
            {
                gridControlKQ.Visible = false;
                gridControlKQDG.Visible = true;
                string timkiem = "select * from AspNetUsers where PhoneNumber like '%" + txtFind.Text + "%'";
                //ketnoi(timkiem);
                //myCommand.ExecuteNonQuery();               
                gridControlKQDG.DataSource = ketnoi(timkiem);
                myConnection.Close();
            }
        }

        private void radTenKH_CheckedChanged(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }
       
        private void radSDT_CheckedChanged(object sender, EventArgs e)
        {
            txtFind.Text = "";
            txtFind.Focus();
        }
    }
}