Imports System.Data.sqlclient
Partial Class home
    Inherits System.Web.UI.Page
    Dim connection As SqlConnection

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Try
            connection = New SqlConnection(ConfigurationManager.ConnectionStrings("GoCloudConnection").ConnectionString)
            If Session("username") = "" Then
                Response.Redirect("login.aspx")
            Else
                If Not Page.IsPostBack Then
                    lblUser.Text = Session("username")
                End If
            End If
        Catch ex As Exception
            'MsgBox(ex.ToString)
        End Try
    End Sub

    Protected Sub lnkLogout_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles lnkLogout.Click
        Try
            Session.RemoveAll()
            Response.Redirect("login.aspx")
        Catch ex As Exception
            'MsgBox(ex.ToString)
        End Try
    End Sub


    Protected Sub lnkMenu_Click(ByVal sender As Object, ByVal e As System.EventArgs)
        Try
            Dim clicked As LinkButton
            clicked = CType(sender, LinkButton)
            Select Case clicked.Text
                Case "Install Software"
                    clearAll()
                    pnlInstallSoftware.Visible = True
                Case "Get License"
                    Response.Redirect("licence.aspx")
                Case "Start Service"
                    clearAll()
                    pnlStartService.Visible = True
                Case "Purchase Software"
                    clearAll()
                    pnlPurchaseSoftware.Visible = True
            End Select
        Catch ex As Exception
            'MsgBox(ex.ToString)
        End Try
    End Sub
    Private Sub clearAll()
        Try
            pnlInstallSoftware.Visible = False
            pnlStartService.Visible = False
            pnlPurchaseSoftware.Visible = False
        Catch ex As Exception
            'MsgBox(ex.ToString)
        End Try
    End Sub

    Protected Sub btnInstallSoftware_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnInstallSoftware.Click
        Try
            Dim software_name, software_url, software_description, start_command, software_ip, software_port As String
            software_name = Trim(txtSoftwareName.Text)
            start_command = Trim(txtStartCommand.Text)
            software_ip = Trim(txtSoftwareIP.Text)
            software_port = Trim(txtSoftwarePort.Text)
            software_description = Trim(txtSoftwareDescription.Text)
            If software_name <> "" And software_description <> "" And fluSoftware.HasFile = True And Command() <> "" And Trim(txtSoftwareDescription.Text) <> "" Then
                fluSoftware.PostedFile.SaveAs(Server.MapPath(".") & "\softwares\" & fluSoftware.PostedFile.FileName)
                software_url = Server.MapPath(".") & "\softwares\" & fluSoftware.PostedFile.FileName
                Dim param_name, param_url, param_installed, param_description, param_status, param_command, param_type, param_ip, param_port As SqlParameter
                param_name = New SqlParameter("@name", Data.SqlDbType.VarChar, 100)
                param_name.Value = software_name
                param_description = New SqlParameter("@description", Data.SqlDbType.VarChar, 2048)
                param_description.Value = software_description
                param_url = New SqlParameter("@url", Data.SqlDbType.VarChar, 300)
                param_url.Value = software_url
                param_installed = New SqlParameter("@installed", Data.SqlDbType.DateTime)
                param_installed.Value = Now
                param_command = New SqlParameter("@command", Data.SqlDbType.VarChar, 1000)
                param_command.Value = start_command
                param_type = New SqlParameter("@type", Data.SqlDbType.SmallInt)
                param_type.Value = drdSoftwareType.SelectedValue
                param_status = New SqlParameter("@status", Data.SqlDbType.SmallInt)
                param_status.Value = 2
                Dim command As SqlCommand
                If software_port <> "" And software_ip <> "" Then
                    param_ip = New SqlParameter("@ip", Data.SqlDbType.VarChar, 100)
                    param_ip.Value = software_ip
                    param_port = New SqlParameter("@port", Data.SqlDbType.VarChar, 10)
                    param_port.Value = software_port
                    command = New SqlCommand("insert into software (software_name, software_url, installed_on, status_id, command, software_type, software_ip, software_port, description) values (@name,@url,@installed, @status,@command, @type, @ip,@port,@description)", connection)
                    command.Parameters.Add(param_ip)
                    command.Parameters.Add(param_port)
                Else
                    command = New SqlCommand("insert into software (software_name, software_url, installed_on, status_id, command, software_type, description) values (@name,@url,@installed, @status,@command, @type, @description)", connection)
                End If
                command.Parameters.Add(param_name)
                command.Parameters.Add(param_url)
                command.Parameters.Add(param_installed)
                command.Parameters.Add(param_command)
                command.Parameters.Add(param_type)
                command.Parameters.Add(param_status)
                command.Parameters.Add(param_description)
                connection.Open()
                command.ExecuteNonQuery()
                connection.Close()
                lblInstallError.Text = "Software successfully installed !"
                txtSoftwareName.Text = ""
                txtStartCommand.Text = ""
                gdvStartService.DataBind()
            Else
                lblInstallError.Text = "Invalid software details !"
            End If
        Catch ex As Exception
            MsgBox(ex.ToString)
            'MsgBox(ex.ToString)
        End Try
    End Sub

    Protected Sub gdvStartService_SelectedIndexChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles gdvStartService.SelectedIndexChanged
        Try
            Shell("cmd /k " & Chr(34) & Server.MapPath(".") & "\softwares\" & gdvStartService.SelectedRow.Cells(2).Text & Chr(34), AppWinStyle.MaximizedFocus)
        Catch ex As Exception
            'MsgBox(ex.ToString)
        End Try
    End Sub

    Protected Sub btnPurchase_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnPurchase.Click
        Dim command As SqlCommand
        Try
            command = New SqlCommand("insert into clouduser_software values ('" & Session("username") & "'," & drdPurchaseSoftware.SelectedValue & ")", connection)
            connection.Open()
            command.ExecuteNonQuery()
            connection.Close()
        Catch ex As Exception
            'MsgBox(ex.ToString)
        End Try
    End Sub
End Class
