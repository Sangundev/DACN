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

namespace LTWin_PMQLTV
{
    public partial class rptInTheDG : DevExpress.XtraScheduler.Reporting.XtraSchedulerReport
    {
        public rptInTheDG()
        {
            InitializeComponent();
        }

        public Image ByteArrayToImage(byte[] byteArrayIn)
        {
            MemoryStream ms = new MemoryStream(byteArrayIn);
            Image returnImage = Image.FromStream(ms);
            return returnImage;
        }

        Image bNewHinhAnh = null;
        public rptInTheDG(Image bHinhAnh):this()
        {
            bNewHinhAnh = bHinhAnh;
            picAnh.Image = bNewHinhAnh;
        }      

        public void Load()
        {
            lblMaThe.DataBindings.Add("Text", DataSource, "Id");
            lblHoTen.DataBindings.Add("Text", DataSource, "StoreName");
            lblNgaySinh.DataBindings.Add("Text", DataSource, "NgaySinh").FormatString = "{0:dd/MM/yyyy}";
            lblDiaChi.DataBindings.Add("Text", DataSource, "Adress");           
            lblTrangThai.DataBindings.Add("Text", DataSource, "IsApproved");
            picAnh.DataBindings.Add("Image", DataSource, "Images");
            picAnh.Image = bNewHinhAnh;
        }
    }
}
