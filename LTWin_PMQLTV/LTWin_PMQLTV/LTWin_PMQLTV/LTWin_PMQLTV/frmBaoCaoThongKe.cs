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
    public partial class frmBaoCaoThongKe : DevExpress.XtraEditors.XtraForm
    {
        public frmBaoCaoThongKe()
        {
            InitializeComponent();
        }

        string strKetNoi = ConfigurationManager.ConnectionStrings["strConn"].ConnectionString;
        private SqlConnection myConnection;
        private SqlCommand myCommand;
        private SqlDataAdapter myDataAdapter;
        private DataTable myTable;


        private SqlDataReader myDataReaderSLDauSach;
        private SqlDataReader myDataReaderSLMuon;
        private SqlDataReader myDataReaderSLDG;
        private SqlDataReader myDataReaderSLDGMuon;
        private SqlDataReader myDataReaderSLSQuaHan;
        private SqlDataReader myDataReaderSLDGQuaHan;


        private DataTable ketnoi(string truyvan)
        {
            myConnection = new SqlConnection(strKetNoi);
            myConnection.Open();
            string thuchiencaulenh = truyvan;
            myCommand = new SqlCommand(thuchiencaulenh, myConnection);
            myDataAdapter = new SqlDataAdapter(myCommand);
            myTable = new DataTable();
            myDataAdapter.Fill(myTable);
            //gridControlDSDGQuaHan.DataSource = myTable;
            return myTable;
        }

        public string luuSLDauSach, luuSLCuon, luuTongGiaTriSach;
        // Tính SL Đầu Sách, SL Cuốn, SL Còn, Tổng GT Sách
        private void slDauSach()
        {
            

        }


        // Tính SL Mượn
        public string luuSLMuon;
        private void slMuon()
        {
           
        }

        // Tính SL Độc Giả
        public string luuSLDG;
        private void slDocGia()
        {
           
        }

        // Tính SLDG Đã Mượn sách
        public string luuSLDGMuon;
        private void slDocGiaMuon()
        {
           
        }

        //SL sách quá hạn
        public string luuSLSQuaHan;
        private void slSachQuaHan()
        {
           

        }

        // SL Độc Giả quá hạn
        public string luuSLDGQuaHan;
        private void slDGQuaHan()

        {
            

        }

        public int luuSLCon=0;
        private void frmReportStatistic_Load(object sender, EventArgs e)
        {
            // Tổng SL Đầu sách, sl nhập, sl còn , tổng giá trị


            LoadStoreIdsToComboBox();
       
        }

        private void LoadStoreIdsToComboBox()
        {
            using (SqlConnection connection = new SqlConnection(strKetNoi))
            {
                connection.Open();

                    string sqlQuery = @"
                SELECT U.Id
                FROM AspNetUsers U
                INNER JOIN AspNetUserRoles UR ON U.Id = UR.UserId
                INNER JOIN AspNetRoles R ON UR.RoleId = R.Id
                WHERE R.Name = 'User'"; // Thay 'User' bằng tên vai trò mong muốn lấy ID

                SqlCommand command = new SqlCommand(sqlQuery, connection);
                SqlDataReader reader = command.ExecuteReader();

                // Xóa các mục cũ trong ComboBox trước khi thêm mới
                comboBoxEdit1.Properties.Items.Clear();

                // Thêm ID vào ComboBox
                while (reader.Read())
                {
                    string userId = reader["Id"].ToString();
                    comboBoxEdit1.Properties.Items.Add(userId);
                }

                reader.Close();
            }
        }

        private void comboBoxEdit1_SelectedIndexChanged(object sender, EventArgs e)
        {
            string selectedStoreId = comboBoxEdit1.SelectedItem?.ToString();

            if (!string.IsNullOrEmpty(selectedStoreId))
            {
                int outOfStockProducts = CalculateOutOfStockProducts(selectedStoreId);
                int customersWithoutCanceledOrders = CalculateCustomersWithoutCanceledOrders(selectedStoreId);
                int customersWithoutOrders = CalculateCustomersWithoutedOrders(selectedStoreId);
                decimal totalRevenue = CalculateTotalRevenue(selectedStoreId);
                int remainingProducts = CalculateRemainingProducts(selectedStoreId);
                DataTable customersPurchasedToday = GetCustomersPurchasedToday(selectedStoreId);
                DataTable canceledOrdersCustomers = GetCanceledOrdersCustomers(selectedStoreId);


                if (totalRevenue > 0)
                {
                    txtDTCH.Text = totalRevenue.ToString();
                }
                else
                {
                    txtDTCH.Text = "0";
                    MessageBox.Show("Không có dữ liệu doanh thu cho cửa hàng này.");
                }
                gridControlDSDGQuaHan.DataSource = canceledOrdersCustomers;

                gridControlDSSachQuaHan.DataSource = customersPurchasedToday;
                txtKHDM.Text = customersWithoutCanceledOrders.ToString(); // Display customers without canceled orders count in txtKHDM
                txtKHHD.Text = customersWithoutOrders.ToString();
                txtSPH.Text = outOfStockProducts.ToString();
                txtSPC.Text = remainingProducts.ToString(); // Display remaining product quantity in txtSPC
            }
            else
            {
                MessageBox.Show("Vui lòng chọn một cửa hàng để thống kê.");
            }
        }

        private DataTable GetCanceledOrdersCustomers(string selectedStoreId)
        {
            DataTable canceledOrdersCustomers = new DataTable();

            if (!string.IsNullOrEmpty(selectedStoreId))
            {
                string sqlQuery = $@"
            SELECT A.Fullname, A.Email, A.UserName
            FROM AspNetUsers A, Order_detail C, Orders B
            WHERE A.Id = B.Od_name AND C.Storeid = '{selectedStoreId}' AND B.Od_id = C.Od_id AND B.VoidanOder = 'TRUE'";

                using (SqlConnection connection = new SqlConnection(strKetNoi))
                {
                    connection.Open();

                    SqlCommand command = new SqlCommand(sqlQuery, connection);
                    SqlDataAdapter adapter = new SqlDataAdapter(command);
                    adapter.Fill(canceledOrdersCustomers);
                }
            }
            else
            {
                MessageBox.Show("Vui lòng chọn một cửa hàng để thống kê khách hàng đã hủy đơn hàng.");
            }

            return canceledOrdersCustomers;
        }

        private DataTable GetCustomersPurchasedToday(string selectedStoreId)
        {
            DataTable customersPurchasedToday = new DataTable();

            if (!string.IsNullOrEmpty(selectedStoreId))
            {
                string sqlQuery = $@"
            SELECT b.Od_id,b.Od_date
            FROM Order_detail a
            JOIN Orders b ON a.Od_id = b.Od_id
            WHERE a.Storeid = '{selectedStoreId}' AND b.Od_date > DATEADD(DAY, -1, GETDATE())";

                using (SqlConnection connection = new SqlConnection(strKetNoi))
                {
                    connection.Open();

                    SqlCommand command = new SqlCommand(sqlQuery, connection);
                    SqlDataAdapter adapter = new SqlDataAdapter(command);
                    adapter.Fill(customersPurchasedToday);
                }
            }
            else
            {
                MessageBox.Show("Vui lòng chọn một cửa hàng để thống kê khách hàng đã mua trong ngày.");
            }

            return customersPurchasedToday;
        }

        private int CalculateCustomersWithoutedOrders(string selectedStoreId)
        {
            int customersWithoutCanceledOrders = 0;

            if (!string.IsNullOrEmpty(selectedStoreId))
            {
                string sqlQuery = $@"
            SELECT COUNT(b.VoidanOder) as CustomersWithoutCanceledOrders
            FROM Order_detail a
            JOIN Orders b ON a.Od_id = b.Od_id
            WHERE a.Storeid = '{selectedStoreId}' AND b.VoidanOder = 'false'";

                using (SqlConnection connection = new SqlConnection(strKetNoi))
                {
                    connection.Open();

                    SqlCommand command = new SqlCommand(sqlQuery, connection);
                    SqlDataReader reader = command.ExecuteReader();

                    if (reader.Read())
                    {
                        customersWithoutCanceledOrders = reader["CustomersWithoutCanceledOrders"] != DBNull.Value ? Convert.ToInt32(reader["CustomersWithoutCanceledOrders"]) : 0;
                    }

                    reader.Close();
                }
            }
            else
            {
                MessageBox.Show("Vui lòng chọn một cửa hàng để thống kê khách hàng đã mua.");
            }

            return customersWithoutCanceledOrders;
        }
        private int CalculateCustomersWithoutCanceledOrders(string selectedStoreId)
        {
            int customersWithoutCanceledOrders = 0;

            if (!string.IsNullOrEmpty(selectedStoreId))
            {
                string sqlQuery = $@"
            SELECT COUNT(b.VoidanOder) as CustomersWithoutCanceledOrders
            FROM Order_detail a
            JOIN Orders b ON a.Od_id = b.Od_id
            WHERE a.Storeid = '{selectedStoreId}' AND b.VoidanOder = 'true'";

                using (SqlConnection connection = new SqlConnection(strKetNoi))
                {
                    connection.Open();

                    SqlCommand command = new SqlCommand(sqlQuery, connection);
                    SqlDataReader reader = command.ExecuteReader();

                    if (reader.Read())
                    {
                        customersWithoutCanceledOrders = reader["CustomersWithoutCanceledOrders"] != DBNull.Value ? Convert.ToInt32(reader["CustomersWithoutCanceledOrders"]) : 0;
                    }

                    reader.Close();
                }
            }
            else
            {
                MessageBox.Show("Vui lòng chọn một cửa hàng để thống kê khách hàng đã mua.");
            }

            return customersWithoutCanceledOrders;
        }
        private int CalculateOutOfStockProducts(string selectedStoreId)
        {
            int outOfStockProducts = 0;

            if (!string.IsNullOrEmpty(selectedStoreId))
            {
                string sqlQuery = $@"
            SELECT COUNT(Productid) as OutOfStockProducts
            FROM Product 
            WHERE Userid = '{selectedStoreId}' AND Soluong <= 0";

                using (SqlConnection connection = new SqlConnection(strKetNoi))
                {
                    connection.Open();

                    SqlCommand command = new SqlCommand(sqlQuery, connection);
                    SqlDataReader reader = command.ExecuteReader();

                    if (reader.Read())
                    {
                        outOfStockProducts = reader["OutOfStockProducts"] != DBNull.Value ? Convert.ToInt32(reader["OutOfStockProducts"]) : 0;
                    }

                    reader.Close();
                }
            }
            else
            {
                MessageBox.Show("Vui lòng chọn một cửa hàng để thống kê số lượng sản phẩm hết hàng.");
            }

            return outOfStockProducts;
        }
        private decimal CalculateTotalRevenue(string selectedStoreId)
        {
            decimal totalRevenue = 0;

            if (!string.IsNullOrEmpty(selectedStoreId))
            {
                string sqlQuery = $@"
            SELECT SUM(tt_money) as TotalRevenue
            FROM Order_detail a
            JOIN Orders b ON a.Od_id = b.Od_id
            WHERE a.Storeid = '{selectedStoreId}'";

                using (SqlConnection connection = new SqlConnection(strKetNoi))
                {
                    connection.Open();

                    SqlCommand command = new SqlCommand(sqlQuery, connection);
                    SqlDataReader reader = command.ExecuteReader();

                    if (reader.Read())
                    {
                        totalRevenue = reader["TotalRevenue"] != DBNull.Value ? Convert.ToDecimal(reader["TotalRevenue"]) : 0;
                    }

                    reader.Close();
                }
            }
            else
            {
                MessageBox.Show("Vui lòng chọn một cửa hàng để thống kê doanh thu.");
            }

            return totalRevenue;
        }

        private int CalculateRemainingProducts(string selectedStoreId)
        {
            int remainingProducts = 0;

            if (!string.IsNullOrEmpty(selectedStoreId))
            {
                string sqlQuery = $@"
            SELECT COUNT(Productid) as TotalProducts
            FROM Product 
            WHERE Userid = '{selectedStoreId}' AND Soluong > 0";

                using (SqlConnection connection = new SqlConnection(strKetNoi))
                {
                    connection.Open();

                    SqlCommand command = new SqlCommand(sqlQuery, connection);
                    SqlDataReader reader = command.ExecuteReader();

                    if (reader.Read())
                    {
                        remainingProducts = reader["TotalProducts"] != DBNull.Value ? Convert.ToInt32(reader["TotalProducts"]) : 0;
                    }

                    reader.Close();
                }
            }
            else
            {
                MessageBox.Show("Vui lòng chọn một cửa hàng để thống kê số lượng sản phẩm còn lại.");
            }

            return remainingProducts;
        }




        private void comboBoxEdit1_Click(object sender, EventArgs e)
        {

        }

        private void chartDG_Click(object sender, EventArgs e)
        {

        }

        private void btnSach_Click(object sender, EventArgs e)
        {
            // Assuming chartSalary is the Chart control

            // Set the visibility of the chart controls
            chartSalary.Visible = true;
            chartDG.Visible = false;

            string selectedStoreId = comboBoxEdit1.SelectedItem?.ToString();

            if (!string.IsNullOrEmpty(selectedStoreId))
            {
                int remainingProducts = CalculateRemainingProducts(selectedStoreId);
                int outOfStockProducts = CalculateOutOfStockProducts(selectedStoreId);

                // Clear any existing data in the chart
                chartSalary.Series.Clear();

                // Add series for remaining products and out-of-stock products
                chartSalary.Series.Add("Remaining Products");
                chartSalary.Series["Remaining Products"].Points.AddXY("Remaining", remainingProducts);

                chartSalary.Series.Add("Out-of-Stock Products");
                chartSalary.Series["Out-of-Stock Products"].Points.AddXY("Out of Stock", outOfStockProducts);

                // Set chart properties (title, axis labels, etc.) if needed
                chartSalary.Titles.Add("Product Quantity Overview");
                chartSalary.ChartAreas[0].AxisX.Title = "Product Status";
                chartSalary.ChartAreas[0].AxisY.Title = "Quantity";
            }
            else
            {
                MessageBox.Show("Vui lòng chọn một cửa hàng để thống kê.");
            }
        }


        private void btnDG_Click(object sender, EventArgs e)
        {
            // Assuming chartDG is the Chart control

            // Set the visibility of the chart controls
            chartSalary.Visible = false;
            chartDG.Visible = true;

            string selectedStoreId = comboBoxEdit1.SelectedItem?.ToString();

            if (!string.IsNullOrEmpty(selectedStoreId))
            {
                int canceledOrdersCount = CalculateCustomersWithoutCanceledOrders(selectedStoreId);
                int purchasedOrdersCount = CalculateCustomersWithoutedOrders(selectedStoreId);

                // Clear any existing data in the chart
                chartDG.Series.Clear();

                // Add series for canceled and purchased orders
                chartDG.Series.Add("Canceled Orders");
                chartDG.Series["Canceled Orders"].Points.AddXY("Canceled", canceledOrdersCount);

                chartDG.Series.Add("Purchased Orders");
                chartDG.Series["Purchased Orders"].Points.AddXY("Purchased", purchasedOrdersCount);

                // Set chart properties (title, axis labels, etc.) if needed
                chartDG.Titles.Add("Order Status Overview");
                chartDG.ChartAreas[0].AxisX.Title = "Order Status";
                chartDG.ChartAreas[0].AxisY.Title = "Count";
            }
            else
            {
                MessageBox.Show("Vui lòng chọn một cửa hàng để thống kê.");
            }
        }



        //select CTMUONTRA.MAMT, MADG, MASACH, NGAYMUON, NGAYHENTRA, SLMUON, TINHTRANG, CTMUONTRA.GHICHU from CTMUONTRA, HSMUONTRA where CTMUONTRA.MAMT = HSMUONTRA.MAMT";
    }
}