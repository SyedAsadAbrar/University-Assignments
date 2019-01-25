<%@ Page Title="" Language="C#" MasterPageFile="~/Layout.Master" AutoEventWireup="true" CodeBehind="Events.aspx.cs" Inherits="DatabaseProject.Events" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <Script>$(document).ready(function () {
            $(".tooltip-class").hover(function () {
                $(this).attr("tooltip-data", $(this).attr("title"));
                $(this).removeAttr("title");
            }, function () {
                $(this).attr("title", $(this).attr("tooltip-data"));
                $(this).removeAttr("tooltip-data");
            });
        });</Script>
    <Style>
        .fa-cart-plus{
            color:black !important;
        }
        .form-control{
            width:100px;
        }
        .inline{
            float:left !important;   
        }
        .ff{
             font-family:"Helvetica Neue", Helvetica, Arial, sans-serif;
        }
      
       
        
    </Style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ShoppingCartPlaceholder" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="NotificationsPlaceholder" runat="server">
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div>
        <h1>Events</h1>
           <asp:Label ID="Message" runat="server" ></asp:Label>
        
        <asp:Repeater ID="Display" runat="server">
            <itemtemplate>
           
            <div class="media  p-3">
                <img src='<%# Eval("ImageLink") %>' alt="<%# Eval("eventName") %>" class="mr-3 mt-3" style="width:200px;height:200px">
                <div class="media-body">
                  <h4><%# Eval("eventName") %> <small><i><%# Eval("Day_Time") %></i></small></h4>
                  <h6>Venue :<small><i> <%# Eval("venue") %></i></small></h6>      
                  <h6>Event Type :<small><i> <%# Eval("eventType") %></i></small></h6>      
                  <h6>Organizers :<small><i> <%# Eval("organizers") %></i></small></h6>      
                  <h6>Cost :<small><i> <%# Eval("cost") %></i></small></h6> 
                    
                                    
                  <h6 class="inline">Quantity:</h6> <input class=" inline form-control"  name='<%# Eval("ticketId")%>' />                 
                 
                  <asp:LinkButton CssClass="inline btn btn-warning tooltip-class"  ToolTip='<%# Eval("ticketId")%>' onclick="AddToCart_Click"  ID="LinkButton1" runat="server">
                      <span class="fa fa-cart-plus fa2x" >
                          <i class="ff">Add to cart</i>
                      </span>
                 </asp:LinkButton> 
               
                </div>

            </div>
           </itemtemplate>
        </asp:Repeater>
    </div>
</asp:Content>
