<%@ Page Language="VB" AutoEventWireup="false" CodeFile="home.aspx.vb" Inherits="home" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
    <head runat="server">
        <title>Go Cloud - My Home</title>
    </head>
    <body>
        <form id="form1" runat="server">
            <div>
                <div>
                    <p style="text-align :right">
                        <b>Welcome <asp:Label ID="lblUser" runat="server"></asp:Label> <asp:LinkButton ID="lnkLogout" runat="server">logout</asp:LinkButton></b>
                    </p>
                </div>
                 <div>
                    <h1>GO CLOUD </h1>
                    <hr />
                </div>
                <br />
                <a href="softwares/CloudComputingClient.jar">Download our cloud client for JAVA applications</a>
                <br />
                <div>
                    <table border="1">
                        <tr>
                            <td>
                                <center>
                                    <asp:DataList ID="DataList1" runat="server" DataSourceID="dsMenu">
                                        <ItemTemplate>
                                            <br />
                                            <asp:LinkButton ID="lnkMenu" runat="server" CommandArgument='<%# Eval("privilege_name") %>'
                                                Text='<%# Eval("privilege_name") %>' OnClick="lnkMenu_Click"></asp:LinkButton><br />                                                
                                        </ItemTemplate>
                                    </asp:DataList>
                                  <asp:SqlDataSource ID="dsMenu" runat="server" ConnectionString="<%$ ConnectionStrings:GoCloudConnection %>"
                                        SelectCommand="SELECT     privilege.privilege_name&#13;&#10;FROM         privilege INNER JOIN&#13;&#10;                      role_privilege ON privilege.privilege_id = role_privilege.privilege_id&#13;&#10;WHERE     (role_privilege.role_id = @role_id)">
                                        <SelectParameters>
                                            <asp:SessionParameter Name="role_id" SessionField="role_id" />
                                        </SelectParameters>
                                    </asp:SqlDataSource>
                                </center>
                            </td>
                            <td>
                                <asp:Panel ID="pnlInstallSoftware" runat="server" Height="100%" Width="100%" Visible="false">
                                    <fieldset>
                                        <legend>Install Software</legend>
                                        <table>
                                            <tr>
                                                <td> Software Type </td>
                                                <td> 
                                                    <asp:DropDownList ID="drdSoftwareType" runat="server" AutoPostBack="True" DataSourceID="dsTypes" DataTextField="type_name" DataValueField="type_id" Width="152px"></asp:DropDownList><asp:SqlDataSource ID="dsTypes" runat="server" ConnectionString="<%$ ConnectionStrings:GoCloudConnection %>"
                                                        SelectCommand="SELECT [type_name], [type_id] FROM [software_type]"></asp:SqlDataSource>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td> Software name </td>
                                                <td> <asp:TextBox ID="txtSoftwareName" runat="server"></asp:TextBox> </td>
                                            </tr>
                                            <tr>
                                                <td> Software File </td>
                                                <td> <asp:FileUpload ID="fluSoftware" runat="server" /> </td>
                                            </tr>
                                            <tr>
                                                <td> Software Description </td>
                                                <td> <asp:TextBox ID="txtSoftwareDescription" runat="server"></asp:TextBox> </td>
                                            </tr>                                            
                                            <tr>
                                                <td> Start Command </td>
                                                <td> <asp:TextBox ID="txtStartCommand" runat="server"></asp:TextBox> </td>
                                            </tr>
                                            <tr>
                                                <td> Software IP </td>
                                                <td> <asp:TextBox ID="txtSoftwareIP" runat="server"></asp:TextBox> </td>
                                            </tr>
                                            <tr>
                                                <td> Software Port No. </td>
                                                <td> <asp:TextBox ID="txtSoftwarePort" runat="server"></asp:TextBox> </td>
                                            </tr>
                                            <tr>
                                                <td> <asp:Label ID="lblInstallError" runat="server" Font-Bold="True" ForeColor="Red"></asp:Label> </td>
                                            </tr>
                                            <tr>
                                                <td> <asp:Button ID="btnInstallSoftware" runat="server" Text="Install" /> </td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                </asp:Panel>
                                <asp:Panel ID="pnlStartService" runat="server" Height="100%" Width="100%" Visible="false">
                                    <fieldset>
                                        <legend>Start service</legend>
                                        <asp:GridView ID="gdvStartService" runat="server" AutoGenerateColumns="False" DataSourceID="dsStartService" Width="32px">
                                            <Columns>
                                                <asp:CommandField SelectText="Start" ShowSelectButton="True" />
                                                <asp:BoundField DataField="software_name" HeaderText="Software" SortExpression="software_name" />
                                                <asp:BoundField DataField="command" HeaderText="Command" SortExpression="command" />
                                            </Columns>
                                        </asp:GridView>
                                        <asp:SqlDataSource ID="dsStartService" runat="server" ConnectionString="<%$ ConnectionStrings:GoCloudConnection %>"
                                            SelectCommand="SELECT [software_name], [command], [software_id] FROM [software] WHERE ([status_id] = @status_id)">
                                            <SelectParameters>
                                                <asp:Parameter DefaultValue="2" Name="status_id" Type="Int16" />
                                            </SelectParameters>
                                        </asp:SqlDataSource>
                                        &nbsp;
                                    </fieldset>
                                </asp:Panel>
                                 <asp:Panel ID="pnlPurchaseSoftware" runat="server" Height="100%" Width="100%" Visible="false">
                                    <fieldset>
                                        <legend>Purchase Software</legend>
                                        <table>
                                            <tr>
                                                <td> Select a software </td>
                                                <td> <asp:DropDownList ID="drdPurchaseSoftware" runat="server" AutoPostBack="True" DataSourceID="dsPurchase" DataTextField="software_name" DataValueField="software_id">
                                                    </asp:DropDownList><asp:SqlDataSource ID="dsPurchase" runat="server" ConnectionString="<%$ ConnectionStrings:GoCloudConnection %>"
                                                    SelectCommand="SELECT [software_id], [software_name] FROM [software]">
                                                </asp:SqlDataSource>
                                               </td>
                                            </tr>
                                            <tr>
                                                <td> <asp:Button ID="btnPurchase" runat="server" Text="Purchase" /> </td>
                                            </tr>
                                            <tr>
                                                <td> <asp:Label ID="lblPurchaseError" runat="server" Font-Bold="True" ForeColor="Red"></asp:Label> </td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                 </asp:Panel>
                            </td>
                        </tr>
                    </table>
                    <hr />
                </div>
            </div>
        </form>
    </body>
</html>
