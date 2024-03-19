using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Linq;
using System.Windows.Forms;
using DevExpress.XtraEditors;

namespace LTWin_PMQLTV
{
    public partial class frmVideo : DevExpress.XtraEditors.XtraForm
    {
        public frmVideo()
        {
            InitializeComponent();
            axWindowsMediaPlayer1.URL = @"C:\Users\admin\Downloads\LTWin_PMQLTV\video.mp4";
            axWindowsMediaPlayer1.Ctlcontrols.play();
        }

        private void frmVideo_Load(object sender, EventArgs e)
        {

        }

        private void axWindowsMediaPlayer1_Enter(object sender, EventArgs e)
        {

        }
    }
}