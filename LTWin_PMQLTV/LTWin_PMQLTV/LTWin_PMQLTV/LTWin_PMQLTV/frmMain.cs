using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Linq;
using System.Windows.Forms;
using DevExpress.XtraEditors;
using System.Threading;

namespace LTWin_PMQLTV
{
    public partial class frmMain : DevExpress.XtraEditors.XtraForm
    {
        public frmMain()
        {
            Thread t = new Thread(new ThreadStart(LTWin_PMQLTV));
            t.Start();
            Thread.Sleep(5000);
            InitializeComponent();
            t.Abort();
        }

        // chạy splash
        private void LTWin_PMQLTV()
        {
            Application.Run(new frmSplashScreen());
        }

        // ham kiem tra form
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

        private void frmMain_Load(object sender, EventArgs e)
        {
            btnCreateAcc.Enabled = true;
            btnChangePass.Enabled = false;
            btnLogOut.Enabled = false;
            rbbSupplier.Visible = false;
            rbbReader.Visible = false;
            rbbBook.Visible = false;
            rbbLoanPay.Visible = false;
            rbbReportStatistic.Visible = false;
            rbbHelpAdmin.Visible = false;

            Form frm = kiemtraform(typeof(frmTrangChu));
            if (frm == null)
            {
                frmTrangChu forms = new frmTrangChu();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void btnHomePage_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmTrangChu));
            if (frm == null)
            {
                frmTrangChu forms = new frmTrangChu();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        // nhận giá trị từ form DN
        //bool bNhanLogin;
        //public frmMain(bool bNewLogin):this()
        //{
        //    bNhanLogin = bNewLogin;
        //    btnCreateAcc.Enabled = bNhanLogin;
        //    btnChangePass.Enabled = bNhanLogin;
        //    btnLogOut.Enabled = bNhanLogin;
        //    rbbSupplier.Visible = bNhanLogin;
        //    rbbReader.Visible = bNhanLogin;
        //    rbbBook.Visible = bNhanLogin;
        //    rbbLoanPay.Visible = bNhanLogin;
        //    rbbReportStatistic.Visible = bNhanLogin;
        //    rbbHelpAdmin.Visible = bNhanLogin;
        //}
        public void Login()
        {
            btnCreateAcc.Enabled = true;
            btnChangePass.Enabled = true;
            btnLogOut.Enabled = true;
            rbbSupplier.Visible = true;
            rbbReader.Visible = true;
            rbbBook.Visible = true;
            rbbLoanPay.Visible = true;
            rbbReportStatistic.Visible = true;
            rbbHelpAdmin.Visible = true;
            btnLogIn.Enabled = false;
        }

        private void btnLogIn_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmDangNhap));
            if (frm == null)
            {
                frmDangNhap forms = new frmDangNhap();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }

            Form fromSearch = kiemtraform(typeof(frmTimKiem));
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

            //Form fromTT = kiemtraform(typeof(frmTrangChu));
            //if (fromSearch == null)
            //{
            //    //frmCreateAcc forms = new frmCreateAcc();
            //    //forms.MdiParent = this;
            //    //forms.Show();
            //}
            //else
            //{
            //    fromTT.Close();
            //}

            Form fromPDFDG = kiemtraform(typeof(frmHD));
            if (fromSearch == null)
            {
                //frmCreateAcc forms = new frmCreateAcc();
                //forms.MdiParent = this;
                //forms.Show();
            }
            else
            {
                fromPDFDG.Close();
            }

            Form fromvideoDG = kiemtraform(typeof(frmVideo));
            if (fromSearch == null)
            {
                //frmCreateAcc forms = new frmCreateAcc();
                //forms.MdiParent = this;
                //forms.Show();
            }
            else
            {
                fromvideoDG.Close();
            }
            
        }

        private void btnCreateAcc_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmTaoTK));
            if (frm == null)
            {
                frmTaoTK forms = new frmTaoTK();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void btnChangePass_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmDoiMK));
            if (frm == null)
            {
                frmDoiMK forms = new frmDoiMK();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void btnLogOut_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            btnCreateAcc.Enabled = false;
            btnChangePass.Enabled = false;
            btnLogOut.Enabled = false;
            rbbSupplier.Visible = false;
            rbbReader.Visible = false;
            rbbBook.Visible = false;
            rbbLoanPay.Visible = false;
            rbbReportStatistic.Visible = false;
            rbbHelpAdmin.Visible = false;
            btnLogIn.Enabled = true;
            this.Close();

            Form fromCreateAcc = kiemtraform(typeof(frmTaoTK));
            if (fromCreateAcc == null)
            {
                //frmCreateAcc forms = new frmCreateAcc();
                //forms.MdiParent = this;
                //forms.Show();
            }
            else
            {
                fromCreateAcc.Close();
            }

            Form fromBooks = kiemtraform(typeof(frmQLSP));
            if (fromBooks == null)
            {
                //frmCreateAcc forms = new frmCreateAcc();
                //forms.MdiParent = this;
                //forms.Show();
            }
            else
            {
                fromBooks.Close();
            }

            Form fromChangePass = kiemtraform(typeof(frmDoiMK));
            if (fromChangePass == null)
            {
                //frmCreateAcc forms = new frmCreateAcc();
                //forms.MdiParent = this;
                //forms.Show();
            }
            else
            {
                fromChangePass.Close();
            }

            Form fromLogIn = kiemtraform(typeof(frmDangNhap));
            if (fromLogIn == null)
            {
                //frmCreateAcc forms = new frmCreateAcc();
                //forms.MdiParent = this;
                //forms.Show();
            }
            else
            {
                fromLogIn.Close();
            }

            

            Form fromReader = kiemtraform(typeof(frmQLCH));
            if (fromReader == null)
            {
                //frmCreateAcc forms = new frmCreateAcc();
                //forms.MdiParent = this;
                //forms.Show();
            }
            else
            {
                fromReader.Close();
            }

            //
            Form fromReportStatistic = kiemtraform(typeof(frmBaoCaoThongKe));
            if (fromReportStatistic == null)
            {
                //frmCreateAcc forms = new frmCreateAcc();
                //forms.MdiParent = this;
                //forms.Show();
            }
            else
            {
                fromReportStatistic.Close();
            }

            Form fromSupplier = kiemtraform(typeof(frmCH));
            if (fromSupplier == null)
            {
                //frmCreateAcc forms = new frmCreateAcc();
                //forms.MdiParent = this;
                //forms.Show();
            }
            else
            {
                fromSupplier.Close();
            }

            //Form fromQLMuon = kiemtraform(typeof(frmQLMuonSach));
            //if (fromQLMuon == null)
            //{
            //    //frmCreateAcc forms = new frmCreateAcc();
            //    //forms.MdiParent = this;
            //    //forms.Show();
            //}
            //else
            //{
            //    fromQLMuon.Close();
            //}

            //Form fromQLTra = kiemtraform(typeof(frmQLTraSach));
            //if (fromQLTra == null)
            //{
            //    //frmCreateAcc forms = new frmCreateAcc();
            //    //forms.MdiParent = this;
            //    //forms.Show();
            //}
            //else
            //{
            //    fromQLTra.Close();
            //}
        }

        private void btnSearch_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmTimKiem));
            if (frm == null)
            {
                frmTimKiem forms = new frmTimKiem();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void btnSupplier_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmCH));
            if (frm == null)
            {
                frmCH forms = new frmCH();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void brnReader_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmQLCH));
            if (frm == null)
            {
                frmQLCH forms = new frmQLCH();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void btnBook_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmQLSP));
            if (frm == null)
            {
                frmQLSP forms = new frmQLSP();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

       

        private void btnReportStatistic_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmBaoCaoThongKe));
            if (frm == null)
            {
                frmBaoCaoThongKe forms = new frmBaoCaoThongKe();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void bbtnReadInsReader_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmHD));
            if (frm == null)
            {
                frmHD forms = new frmHD();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void btnSeeInsReader_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmVideo));
            if (frm == null)
            {
                frmVideo forms = new frmVideo();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void btnReadInsAdmin_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmHD1));
            if (frm == null)
            {
                frmHD1 forms = new frmHD1();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void btnSeeInsAdmin_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmVideo1));
            if (frm == null)
            {
                frmVideo1 forms = new frmVideo1();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void btnExit_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            this.Close();
        }


        private void btnQLMuonSach_ItemClick_1(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmQLKH));
            if (frm == null)
            {
                frmQLKH forms = new frmQLKH();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        //private void btnQLTraSach_ItemClick_1(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        //{
        //    Form frm = kiemtraform(typeof(frmQLTraSach));
        //    if (frm == null)
        //    {
        //        frmQLTraSach forms = new frmQLTraSach();
        //        forms.MdiParent = this;
        //        forms.Show();
        //    }
        //    else
        //    {
        //        frm.Activate();
        //    }
        //}

        private void barButtonItem1_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmNPT));
            if (frm == null)
            {
                frmNPT forms = new frmNPT();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }
        }

        private void ribbonControl1_Click(object sender, EventArgs e)
        {

        }

        private void btnQLMuonSach_ItemClick(object sender, DevExpress.XtraBars.ItemClickEventArgs e)
        {
            Form frm = kiemtraform(typeof(frmQLKH));
            if (frm == null)
            {
                frmQLKH forms = new frmQLKH();
                forms.MdiParent = this;
                forms.Show();
            }
            else
            {
                frm.Activate();
            }

        }
    }
}