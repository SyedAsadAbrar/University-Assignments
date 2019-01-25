using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.SqlClient;

namespace DbProjectOld
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
    }
}