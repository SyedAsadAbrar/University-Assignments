using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.HtmlControls;
using DatabaseProject;
using System.Data;

namespace DatabaseProject
{
    public partial class Layouy : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if(Session["user"]!=null)
            {
                myDAL objmyDAl = new myDAL();
                String userId = Session["user"].ToString();
                int result = 0;
                int found = 1;
                found = objmyDAl.getEventCount_DAL(userId, ref result);
                if (found != 1)
                {
                    //execption
                }
                else
                {
                    if(result>0)
                    {
                        notificcationBadge.Text = result.ToString();
                       
                    }
                    else
                    {
                        NumOfEvents.Text = "No event near you";
                    }
                    String EventName = null, EventVenue = null, EventDate = null;
                    int r = 0;
                    HtmlGenericControl divcontrol = new HtmlGenericControl();
                    divcontrol.Attributes["class"] = "well";
                    divcontrol.TagName = "div";
                    for (int i = 0; i < result; i++)
                    {

                        found = objmyDAl.findNearbyEvent_DAL(userId,i+1,ref EventName,ref EventVenue, ref EventDate);
                        if(found==1)
                            divcontrol.InnerHtml += "<div class=\"media border border-left-0 border-right-0 border-top-0 p-3\" ><div class=\"media-body\"><h4>" + EventName + "<small><i>  " + EventDate + "</i></small></h4><p>" + EventVenue + "</p></div></div>";


                    }
                    NotificationsPanel.Controls.Add(divcontrol);
                }
            }
            if (Session["user"] != null)
            {
                myDAL objmyDal1 = new myDAL();
                String user = Session["user"].ToString();
                int f = 1;
                DataTable DT = new DataTable();
                f = objmyDal1.ShopingCart_DAL(user, ref DT);
                if (f == 1)
                {
                    list.DataSource = DT;
                    list.DataBind();
                }

            }
        }
        protected void Logout_Click(object sender, EventArgs e)
        {
            Session["user"] = null;
            Session["type"] = null;
            Response.Redirect("./Home.aspx");
        }
        

        protected void DropCart_Click(object sender, EventArgs e)
        {
            if (Session["user"] != null)
            {
                String User = Session["user"].ToString();
                int found = 1;
                myDAL objmyDal1 = new myDAL();
                found = objmyDal1.dropCart_DAL(User);
                if (found == 1)
                {
                    Response.Redirect("./Home.aspx");
                }
                else
                {
                    Message.Text = "Error Accord";
                }
            }
        }

        protected void DropItem_Click(object sender, EventArgs e)
        {
            if (Session["user"] != null)
            {
                LinkButton b = sender as LinkButton;
                String TicketId = b.ToolTip.ToString();
                String User = Session["user"].ToString();
                int found = 1;
                myDAL objmyDal1 = new myDAL();
                found = objmyDal1.dropItem_DAL(User, TicketId);
                if(found==1)
                {
                    Message.Text = "Successful removal ";
                }
                else
                {
                    Message.Text = "Error Accord";
                }
            }
        }
        
    }
}