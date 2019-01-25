<%@ Page Title="" Language="C#" MasterPageFile="~/Layout.Master" AutoEventWireup="true" CodeBehind="Events.aspx.cs" Inherits="DatabaseProject.Events" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script>// Starrr plugin (https://github.com/dobtco/starrr)
        var __slice = [].slice;

        (function ($, window) {
            var Starrr;

            Starrr = (function () {
                Starrr.prototype.defaults = {
                    rating: void 0,
                    numStars: 5,
                    change: function (e, value) { }
                };

                function Starrr($el, options) {
                    var i, _, _ref,
                        _this = this;

                    this.options = $.extend({}, this.defaults, options);
                    this.$el = $el;
                    _ref = this.defaults;
                    for (i in _ref) {
                        _ = _ref[i];
                        if (this.$el.data(i) != null) {
                            this.options[i] = this.$el.data(i);
                        }
                    }
                    this.createStars();
                    this.syncRating();
                    this.$el.on('mouseover.starrr', 'span', function (e) {
                        return _this.syncRating(_this.$el.find('span').index(e.currentTarget) + 1);
                    });
                    this.$el.on('mouseout.starrr', function () {
                        return _this.syncRating();
                    });
                    this.$el.on('click.starrr', 'span', function (e) {
                        return _this.setRating(_this.$el.find('span').index(e.currentTarget) + 1);
                    });
                    this.$el.on('starrr:change', this.options.change);
                }

                Starrr.prototype.createStars = function () {
                    var _i, _ref, _results;

                    _results = [];
                    for (_i = 1, _ref = this.options.numStars; 1 <= _ref ? _i <= _ref : _i >= _ref; 1 <= _ref ? _i++ : _i--) {
                        _results.push(this.$el.append("<span class='glyphicon .glyphicon-star-empty'></span>"));
                    }
                    return _results;
                };

                Starrr.prototype.setRating = function (rating) {
                    if (this.options.rating === rating) {
                        rating = void 0;
                    }
                    this.options.rating = rating;
                    this.syncRating();
                    return this.$el.trigger('starrr:change', rating);
                };

                Starrr.prototype.syncRating = function (rating) {
                    var i, _i, _j, _ref;

                    rating || (rating = this.options.rating);
                    if (rating) {
                        for (i = _i = 0, _ref = rating - 1; 0 <= _ref ? _i <= _ref : _i >= _ref; i = 0 <= _ref ? ++_i : --_i) {
                            this.$el.find('span').eq(i).removeClass('glyphicon-star-empty').addClass('glyphicon-star');
                        }
                    }
                    if (rating && rating < 5) {
                        for (i = _j = rating; rating <= 4 ? _j <= 4 : _j >= 4; i = rating <= 4 ? ++_j : --_j) {
                            this.$el.find('span').eq(i).removeClass('glyphicon-star').addClass('glyphicon-star-empty');
                        }
                    }
                    if (!rating) {
                        return this.$el.find('span').removeClass('glyphicon-star').addClass('glyphicon-star-empty');
                    }
                };

                return Starrr;

            })();
            return $.fn.extend({
                starrr: function () {
                    var args, option;

                    option = arguments[0], args = 2 <= arguments.length ? __slice.call(arguments, 1) : [];
                    return this.each(function () {
                        var data;

                        data = $(this).data('star-rating');
                        if (!data) {
                            $(this).data('star-rating', (data = new Starrr($(this), option)));
                        }
                        if (typeof option === 'string') {
                            return data[option].apply(data, args);
                        }
                    });
                }
            });
        })(window.jQuery, window);

        $(function () {
            return $(".starrr").starrr();
        });

        $(document).ready(function () {

            $('#stars').on('starrr:change', function (e, value) {
                $('#count').html(value);
            });

            $('#stars-existing').on('starrr:change', function (e, value) {
                $('#count-existing').html(value);
            });
        });</script>


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
        <h1>Events</h1>
        <asp:Label ID="Message" runat="server"></asp:Label>

        <asp:Repeater ID="Display" runat="server">
            <ItemTemplate>

                <div class="media  p-3">
                    <img src='<%# Eval("ImageLink") %>' alt="<%# Eval("eventName") %>" class="mr-3 mt-3" style="width: 200px; height: 200px">
                    <div class="media-body">
                        <h4><%# Eval("eventName") %> <small><i><%# Eval("Day_Time") %></i></small></h4>
                        <h6>Venue :<small><i> <%# Eval("venue") %></i></small></h6>
                        <h6>Event Type :<small><i> <%# Eval("eventType") %></i></small></h6>
                        <div class="align ">
                            <div class="container">
                                <div class="row lead">
                                    <div id="stars" class="starrr"></div>
                                </div>
                            </div>
                        </div>
                        <h6>Organizers :<small><i> <%# Eval("organizers") %></i></small></h6>
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
