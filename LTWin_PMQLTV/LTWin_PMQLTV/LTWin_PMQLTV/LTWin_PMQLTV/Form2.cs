using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.Button;

namespace LTWin_PMQLTV
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }
        string strKetNoi = ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
        private SqlConnection myConnection; // kết nối tới csdl
        private SqlDataAdapter myDataAdapter;   // Vận chuyển csdl qa DataSet
        private DataTable myTable;  // Dùng để lưu bảng lấy từ c#
        SqlCommand myCommand;   // Thực hiện cách lệnh truy vấn
        private void groupControl1_Paint(object sender, PaintEventArgs e)
        {

        }
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
        //void SetTT(tblThuThu item)
        //{
        //    if (item != null)
        //    {
        //        txtMaDG.Text = item.;
        //        txtSoDT.Text = item.DIACHI;
        //        radGTNam.Checked = (item.GIOITINH == "Nam") ? true : false;
        //        dtmNgaySinh.Value = item.NAMSINH.Value;
        //    }
        //}

    }
}
