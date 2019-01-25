﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.SqlClient;

namespace Lab10StudentSearch.DAL
{
    public class myDAL
    {
        private static readonly string connString =
            System.Configuration.ConfigurationManager.ConnectionStrings["sqlCon1"].ConnectionString;

        //Input: Name: StudentName which is to be searched
        //Input DataTable passed by refence and result will be return in this table.
        //Return int: 1 if successful 0 if error.

        public int SearchStudentByName_DAL(string Name, ref DataTable result, ref int retv)
        {
           // int Found = 0;
            DataSet ds = new DataSet();
            SqlConnection con = new SqlConnection(connString);
            con.Open();
            SqlCommand cmd;
            try
            {
                cmd = new SqlCommand("SearchStudentByName ", con); //name of your SQL procedure
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.Add("@Name", SqlDbType.VarChar, 15); //input of SQL stored procedure

                cmd.Parameters.Add(new SqlParameter("@value", SqlDbType.Int));
                cmd.Parameters["@value"].Direction = ParameterDirection.Output;

                // set SQL procedure parameter values
                cmd.Parameters["@Name"].Value = Name;


                cmd.ExecuteNonQuery(); //executre the cmd query


                using (SqlDataAdapter da = new SqlDataAdapter(cmd))
                    da.Fill(ds); //get results

                result = ds.Tables[0]; //fill the results in ref input table

                con.Close();

                retv = int.Parse(cmd.Parameters["@value"].Value.ToString());
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