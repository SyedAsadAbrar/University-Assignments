using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.SqlClient;


namespace DatabaseProject
{
    public class myDAL
    {
        private static readonly string connString =
            System.Configuration.ConfigurationManager.ConnectionStrings["sqlCon1"].ConnectionString;
        //Input: Name: StudentName which is to be searched
        //Input DataTable passed by refence and result will be return in this table.
        //Return int: 1 if successful 0 if error.

        public int Login_DAL(string Email, string Password, ref int retv)

        {

            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("logincheck", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.Add("@email", SqlDbType.VarChar, 30); //input of SQL stored procedure
                cmd.Parameters.Add("@password", SqlDbType.VarChar, 32);

                // set SQL procedure parameter values
                cmd.Parameters["@email"].Value = Email;
                cmd.Parameters["@password"].Value = Password;

                cmd.Parameters.Add(new SqlParameter("@output", SqlDbType.Int));
                cmd.Parameters["@output"].Direction = ParameterDirection.Output;


                cmd.ExecuteNonQuery(); //executre the cmd query

                con.Close(); //close SQL connection

                retv = int.Parse(cmd.Parameters["@output"].Value.ToString());
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }
        public int SignUp_DAL(string Fname, string Lname, string City, string Gender, string Phone, string Email, string Password, ref int retv)

        {

            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("signup", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.Add("@email", SqlDbType.VarChar, 30); //input of SQL stored procedure
                cmd.Parameters.Add("@password", SqlDbType.VarChar, 32);
                cmd.Parameters.Add("@fname", SqlDbType.VarChar, 20);
                cmd.Parameters.Add("@lname", SqlDbType.VarChar, 20);
                cmd.Parameters.Add("@city", SqlDbType.VarChar, 20);
                cmd.Parameters.Add("@gender", SqlDbType.Char, 1);
                cmd.Parameters.Add("@phone", SqlDbType.BigInt);


                // set SQL procedure parameter values
                cmd.Parameters["@email"].Value = Email;
                cmd.Parameters["@password"].Value = Password;
                cmd.Parameters["@fname"].Value = Fname;
                cmd.Parameters["@lname"].Value = Lname;
                cmd.Parameters["@city"].Value = City;
                cmd.Parameters["@phone"].Value = Phone;
                cmd.Parameters["@gender"].Value = Gender;

                cmd.Parameters.Add(new SqlParameter("@output", SqlDbType.Int));
                cmd.Parameters["@output"].Direction = ParameterDirection.Output;


                cmd.ExecuteNonQuery(); //executre the cmd query

                con.Close(); //close SQL connection

                retv = int.Parse(cmd.Parameters["@output"].Value.ToString());
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }
        public int ViewUser_DAL(String Email, ref String Fname, ref String Lname, ref String Phone, ref String City)

        {

            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("viewInfo", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.Add("@email", SqlDbType.VarChar, 30); //input of SQL stored procedure
               
                    

                // set SQL procedure parameter values
                cmd.Parameters["@email"].Value = Email;
                

                cmd.Parameters.Add(new SqlParameter("@fname", SqlDbType.VarChar,20));
                cmd.Parameters["@fname"].Direction = ParameterDirection.Output;

                cmd.Parameters.Add(new SqlParameter("@lname", SqlDbType.VarChar, 20));
                cmd.Parameters["@lname"].Direction = ParameterDirection.Output;

                cmd.Parameters.Add(new SqlParameter("@phone", SqlDbType.BigInt));
                cmd.Parameters["@phone"].Direction = ParameterDirection.Output;

                cmd.Parameters.Add(new SqlParameter("@city", SqlDbType.VarChar, 20));
                cmd.Parameters["@city"].Direction = ParameterDirection.Output;


                cmd.ExecuteNonQuery(); //executre the cmd query

                con.Close(); //close SQL connection

                Fname = cmd.Parameters["@fname"].Value.ToString();
                Lname = cmd.Parameters["@lname"].Value.ToString();
                Phone = "+"+cmd.Parameters["@phone"].Value.ToString();
                City = cmd.Parameters["@city"].Value.ToString();
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }
        public int changePassword_DAL(string Email, string OldPassword, string NewPassword, ref int retv)

        {

            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("changePassword", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.Add("@email", SqlDbType.VarChar, 30); //input of SQL stored procedure
                cmd.Parameters.Add("@opass", SqlDbType.VarChar, 32);
                cmd.Parameters.Add("@npass", SqlDbType.VarChar, 32);

                // set SQL procedure parameter values
                cmd.Parameters["@email"].Value = Email;
                cmd.Parameters["@opass"].Value = OldPassword;
                cmd.Parameters["@npass"].Value = NewPassword;


                cmd.Parameters.Add(new SqlParameter("@output", SqlDbType.Int));
                cmd.Parameters["@output"].Direction = ParameterDirection.Output;


                cmd.ExecuteNonQuery(); //executre the cmd query

                con.Close(); //close SQL connection

                retv = int.Parse(cmd.Parameters["@output"].Value.ToString());
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }

        public int getEventCount_DAL(String Email,ref int retv)
        {

            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("eventCount", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.Add("@email", SqlDbType.VarChar, 30); //input of SQL stored procedure
               

                // set SQL procedure parameter values
                cmd.Parameters["@email"].Value = Email;
                ;


                cmd.Parameters.Add(new SqlParameter("@output", SqlDbType.Int));
                cmd.Parameters["@output"].Direction = ParameterDirection.Output;


                cmd.ExecuteNonQuery(); //executre the cmd query

                con.Close(); //close SQL connection

                retv = int.Parse(cmd.Parameters["@output"].Value.ToString());
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }
       


        public int findNearbyEvent_DAL(String Email,int index, ref String EventName, ref String EventVenue, ref String EventDate)
        {

            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("nearbyEvents", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.Add("@email", SqlDbType.VarChar, 30); //input of SQL stored procedure
                cmd.Parameters.Add("@index", SqlDbType.Int); //input of SQL stored procedure


                // set SQL procedure parameter values
                cmd.Parameters["@email"].Value = Email;
                cmd.Parameters["@index"].Value = index;



                cmd.Parameters.Add(new SqlParameter("@ename", SqlDbType.VarChar,30));
                cmd.Parameters["@ename"].Direction = ParameterDirection.Output;

                cmd.Parameters.Add(new SqlParameter("@evenue", SqlDbType.VarChar, 100));
                cmd.Parameters["@evenue"].Direction = ParameterDirection.Output;

                cmd.Parameters.Add(new SqlParameter("@edate", SqlDbType.DateTime));
                cmd.Parameters["@edate"].Direction = ParameterDirection.Output;


                cmd.ExecuteNonQuery(); //executre the cmd query

                con.Close(); //close SQL connection

                EventName = cmd.Parameters["@ename"].Value.ToString();
                EventVenue = cmd.Parameters["@evenue"].Value.ToString();
                EventDate = cmd.Parameters["@edate"].Value.ToString();
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }
        public int SelectEvents_DAL(ref DataTable result)

        {

            int Found = 0;
            DataSet ds = new DataSet();
            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("selectEvents", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.ExecuteNonQuery(); //executre the cmd query
                using (SqlDataAdapter da = new SqlDataAdapter(cmd))
                    da.Fill(ds); //get results
                result = ds.Tables[0]; //fill the results in ref input table 
                con.Close(); //close SQL connection
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }
        public int SelectConcerts_DAL(ref DataTable result)
        {
            DataSet ds = new DataSet();
            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("selectConcerts", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.ExecuteNonQuery(); //executre the cmd query
                using (SqlDataAdapter da = new SqlDataAdapter(cmd))
                    da.Fill(ds); //get results
                result = ds.Tables[0]; //fill the results in ref input table 
                con.Close(); //close SQL connection
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }
        public int SelectMovies_DAL(ref DataTable result)
        {
            DataSet ds = new DataSet();
            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("selectMovies", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.ExecuteNonQuery(); //executre the cmd query
                using (SqlDataAdapter da = new SqlDataAdapter(cmd))
                    da.Fill(ds); //get results
                result = ds.Tables[0]; //fill the results in ref input table 
                con.Close(); //close SQL connection
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }
        public int AddItemToCart_DAL(String User,String ticketId,String Quantity,ref int retv)
        {
            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            int ex;
           try
           {
                cmd = new SqlCommand("addToCart", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.Add(new SqlParameter("@user", SqlDbType.VarChar, 30));
               
                cmd.Parameters.Add(new SqlParameter("@ticket_id", SqlDbType.Int));
               
                cmd.Parameters.Add(new SqlParameter("@amount", SqlDbType.Int));
               
                cmd.Parameters["@user"].Value = User;
                cmd.Parameters["@ticket_id"].Value = int.Parse(ticketId);
                cmd.Parameters["@amount"].Value = int.Parse(Quantity);

                cmd.Parameters.Add(new SqlParameter("@ex", SqlDbType.Int));
                cmd.Parameters["@ex"].Direction = ParameterDirection.Output;
                cmd.Parameters.Add(new SqlParameter("@done", SqlDbType.Int));
                cmd.Parameters["@done"].Direction = ParameterDirection.Output;

                cmd.ExecuteNonQuery(); //executre the cmd query
                retv = int.Parse(cmd.Parameters["@done"].Value.ToString());
                ex = int.Parse(cmd.Parameters["@ex"].Value.ToString());

                con.Close(); //close SQL connection
           }
           catch
           {
              return 0;
           }
           finally
           {

             con.Close(); //close SQL connection
           }
            return ex;
        }
        public int ShopingCart_DAL(String User,ref DataTable result)
        {
            DataSet ds = new DataSet();
            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("displayCart", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add(new SqlParameter("@user", SqlDbType.VarChar, 30));
                cmd.Parameters["@user"].Value = User;

                cmd.ExecuteNonQuery(); //executre the cmd query

                using (SqlDataAdapter da = new SqlDataAdapter(cmd))
                    da.Fill(ds); //get results

                result = ds.Tables[0]; //fill the results in ref input table 
                con.Close(); //close SQL connection
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }
        public int dropItem_DAL(String User,String TicketId)
        {
            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("dropItem", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add(new SqlParameter("@user", SqlDbType.VarChar, 30));
                cmd.Parameters["@user"].Value = User;
                cmd.Parameters.Add(new SqlParameter("@ticketId", SqlDbType.Int));
                cmd.Parameters["@ticketId"].Value = int.Parse(TicketId.ToString());

                cmd.ExecuteNonQuery(); //executre the cmd query
                con.Close();
            }
            catch(SqlException ex)
            {
                return 0;
            }
            finally
            {
                con.Close();
            }
            return 1;
        }
        public int dropCart_DAL(String User)
        {
            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("dropCart", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.Add(new SqlParameter("@user", SqlDbType.VarChar, 30));
                cmd.Parameters["@user"].Value = User;
                
                cmd.ExecuteNonQuery(); //executre the cmd query
                con.Close();
            }
            catch (SqlException ex)
            {
                return 0;
            }
            finally
            {
                con.Close();
            }
            return 1;
        }
        public int Upcoming_DAL(ref DataTable result)
        {
            DataSet ds = new DataSet();
            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("upcoming", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.ExecuteNonQuery(); //executre the cmd query
                using (SqlDataAdapter da = new SqlDataAdapter(cmd))
                    da.Fill(ds); //get results
                result = ds.Tables[0]; //fill the results in ref input table 
                con.Close(); //close SQL connection
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;
        }

        public int MessageSend_DAL(string email, string message, ref int retv)
        {
            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("ContactMessage", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.Add("@email", SqlDbType.VarChar, 30); //input of SQL stored procedure
                cmd.Parameters.Add("@message", SqlDbType.VarChar, 200);


                // set SQL procedure parameter values
                cmd.Parameters["@email"].Value = email;
                cmd.Parameters["@message"].Value = message;

                cmd.Parameters.Add(new SqlParameter("@output", SqlDbType.Int));
                cmd.Parameters["@output"].Direction = ParameterDirection.Output;


                cmd.ExecuteNonQuery(); //executre the cmd query

                con.Close(); //close SQL connection

                retv = int.Parse(cmd.Parameters["@output"].Value.ToString());
            }
            catch (SqlException ex)
            {
                return 0; //if any erron return 0

            }
            finally
            {
                con.Close();
            }

            return 1; //if no error return 1;

        }
    }
}