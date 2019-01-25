<%@ Page Title="" Language="C#" MasterPageFile="~/Layout.Master" AutoEventWireup="true" CodeBehind="Movies.aspx.cs" Inherits="DatabaseProject.Movies" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <script>$(document).ready(function () {
            $(".tooltip-class").hover(function () {
                $(this).attr("tooltip-data", $(this).attr("title"));
                $(this).removeAttr("title");
            }, function () {
                $(this).attr("title", $(this).attr("tooltip-data"));
                $(this).removeAttr("tooltip-data");
            });
        });</script>
    <style>
        .fa-cart-plus {
            color: black !important;
        }

        .form-control {
            width: 100px;
        }

        .inline {
            float: left !important;
        }

        .ff {
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ShoppingCartPlaceholder" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="NotificationsPlaceholder" runat="server">
</asp:Content>
<asp:Content ID="Content4" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div>
        <h1>Movie Showtimes</h1>
        <asp:Label ID="Message" runat="server"></asp:Label>

        <asp:Repeater ID="Display" runat="server">
            <ItemTemplate>

                <div class="media  p-3">
                    <img src='<%# Eval("ImageLink") %>' alt="<%# Eval("movieName") %>" class="mr-3 mt-3" style="width: 220px; height: 220px">
                    <div class="media-body">
                        <h4><%# Eval("movieName") %> <small><i><%# Eval("date_time") %></i></small></h4>
                        <h6>Genre :<small><i> <%# Eval("genre") %></i></small></h6>
                        <h6>Main Cast :<small><i> <%# Eval("mainCast") %></i></small></h6>
                        <h6>Director :<small><i> <%# Eval("director") %></i></small></h6>
                        <h6>Cinema :<small><i> <%# Eval("Name") %></i></small></h6>
                        <h6>Location :<small><i> <%# Eval("Location") %></i></small></h6>
                        <h6>Screening Type :<small><i> <%# Eval("ScreeningType") %></i></small></h6>
                        <h6>Cost :<small><i> <%# Eval("cost") %></i></small></h6>


                        <h6 class="inline">Quantity:</h6>
                        <input class=" inline form-control" name='<%# Eval("ticketId")%>' />

                        <asp:LinkButton CssClass="inline btn btn-warning tooltip-class" ToolTip='<%# Eval("ticketId")%>' OnClick="AddToCart_Click" ID="LinkButton1" runat="server">
                      <span class="fa fa-cart-plus fa2x" >
                          <i class="ff">Add to cart</i>
                      </span>
                        </asp:LinkButton>

                    </div>

                </div>
            </ItemTemplate>
        </asp:Repeater>
    </div>
</asp:Content>
