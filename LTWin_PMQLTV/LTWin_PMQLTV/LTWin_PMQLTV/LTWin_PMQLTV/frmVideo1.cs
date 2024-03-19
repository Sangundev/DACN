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
    public partial class frmVideo1 : DevExpress.XtraEditors.XtraForm
    {
        public frmVideo1()
        {
            InitializeComponent();
            axWindowsMediaPlayer1.URL = @"E:\HOCTAP\MHHDL\BaoCao\LTWin_PMQLTV\LTWin_PMQLTV\LTWin_PMQLTV\bin\video\HDTT.mp4";
            axWindowsMediaPlayer1.Ctlcontrols.play();
        }
    }
}