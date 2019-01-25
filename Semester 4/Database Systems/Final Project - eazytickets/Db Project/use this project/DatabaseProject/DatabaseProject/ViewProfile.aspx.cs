using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using DatabaseProject;
using System.Web.UI.HtmlControls;
using System.Data;

namespace DatabaseProject
{
    public partial class ViewProfile : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["user"] != null)
            {
                HtmlLink link = Page.Master.FindControl("layoutStyleSheet") as HtmlLink;
                link.Href = "css/secondary.css";

                UserId.Text = Session["user"].ToString();
                myDAL objmyDAl = new myDAL();
                String InputEmail = Session["user"].ToString();
                String fname=null, lname=null, phone=null,city=null;
                int found = 1;
                found = objmyDAl.ViewUser_DAL(InputEmail,ref fname,ref lname,ref phone,ref city);
                if (found == 1)
                {
                    Fname.Text = fname;
                    Lname.Text = lname;
                    Phone.Text = phone;
                    City.Text = city;
                }
            }
        }
    }
}