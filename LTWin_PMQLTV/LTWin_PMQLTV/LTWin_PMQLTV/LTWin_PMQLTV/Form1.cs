using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace LTWin_PMQLTV
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            string Street = textBox1.Text;
            string City = textBox2.Text;


            StringBuilder sb = new StringBuilder();
            sb.Append("http://google.com/maps?q=");

            if (Street != String.Empty)
            {
                sb.Append(Street + "," + "+");
            }
            if (City != String.Empty)
            {
                sb.Append(City + "," + "+");
            }
            webBrowser1.Navigate(sb.ToString());
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Hide();
        }
    }
}
