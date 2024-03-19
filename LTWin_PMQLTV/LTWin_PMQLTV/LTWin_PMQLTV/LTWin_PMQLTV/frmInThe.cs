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
    public partial class frmInThe : DevExpress.XtraEditors.XtraForm
    {
        public frmInThe()
        {
            InitializeComponent();
        }

        // Khai báo kết nối
        string strKetNoi = ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
        private SqlConnection myConnection; // kết nối tới csdl
        private SqlDataAdapter myDataAdapter;   // Vận chuyển csdl qa DataSet
        private DataTable myTable;  // Dùng để lưu bảng lấy từ c#
        SqlCommand myCommand;   // Thực hiện cách lệnh truy vấn
        rptInTheDG rpt = new rptInTheDG();

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
        //
        public Image myImage = null;
        public Image ByteArrayToImage(byte[] byteArrayIn)
        {
            MemoryStream ms = new MemoryStream(byteArrayIn);
            Image returnImage = Image.FromStream(ms);
            return returnImage;
        }

        // nhận dữ liệu từ bảng AspNetUsers
        private string strTenCH;
        public frmInThe(string StoreName):this()
        {
            
            strTenCH = StoreName;
            
            string kn = "select * from AspNetUsers where StoreName='" + strTenCH + "'";
            rpt.DataSource = ketnoi(kn);
            
            byte[] bimg = (byte[])myTable.Rows[0][24];
            myImage = ByteArrayToImage((byte[])myTable.Rows[0][24]);

            rptInTheDG newrpt = new rptInTheDG(myImage);
            
            rpt.Load();
            documentViewer1.PrintingSystem = rpt.PrintingSystem;
            rpt.CreateDocument();
        }

        private void frmInTheDG_Load(object sender, EventArgs e)
        {
            
        }
    }
}