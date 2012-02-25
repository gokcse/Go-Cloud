Imports System.data.sqlclient

Partial Class _Default
    Inherits System.Web.UI.Page
    Dim connection As SqlConnection
    Dim reader As SqlDataReader

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Dim username As String
        If Session("username") <> "" Then
            Try
                username = Session("username")
                connection = New SqlConnection(ConfigurationManager.ConnectionStrings("GoCloudConnection").ConnectionString)
                connection.Open()
                Dim command As SqlCommand
                Dim reader As SqlDataReader
                Response.ContentType = "text/xml"
                Response.Write("<?xml version=" & Chr(34) & "1.0" & Chr(34) & "?>")
                Response.Write("<cloud-application>")
                Response.Write("<owner>")
                Response.Write("<username>")
                Response.Write(username)
                Response.Write("</username>")
                Response.Write("<mac-address>")
                command = New SqlCommand("select mac_address from cloud_user where user_name='" + username + "'", connection)
                reader = command.ExecuteReader
                If reader.Read Then
                    Response.Write(reader.Item(0))
                End If
                reader.Close()
                Response.Write("</mac-address>")
                Response.Write("<root-directory>")
                Response.Write(username)
                Response.Write("</root-directory>")
                Response.Write("</owner>")
                Response.Write("<licence>")
                Response.Write("<applications>")
                Response.Write("<java-applications>")

                command = New SqlCommand("select software_name,software_ip, software_port, command from software, cloud_user, clouduser_software where cloud_user.user_name='" + username + "' and software.software_id=clouduser_software.software_id and cloud_user.user_name=clouduser_software.user_name and software.software_type=1", connection)
                reader = command.ExecuteReader
                While reader.Read
                    Response.Write("<java-application>")
                    Response.Write("<name>")
                    Response.Write(reader.Item(0))
                    Response.Write("</name>")
                    Response.Write("<description>")
                    Response.Write(reader.Item(0))
                    Response.Write("</description>")
                    Response.Write("<service-ip>")
                    Response.Write(reader.Item(1))
                    Response.Write("</service-ip>")
                    Response.Write("<service-port>")
                    Response.Write(reader.Item(2))
                    Response.Write("</service-port>")
                    Response.Write("<command>")
                    Response.Write(reader.Item(3))
                    Response.Write("</command>")
                    Response.Write("</java-application>")
                End While
                reader.Close()
                Response.Write("</java-applications>")

                Response.Write("<windows-applications>")

                command = New SqlCommand("select software_name,software_ip, software_port, command from software, cloud_user, clouduser_software where cloud_user.user_name='" + username + "' and software.software_id=clouduser_software.software_id and cloud_user.user_name=clouduser_software.user_name and software.software_type=2", connection)
                reader = command.ExecuteReader

                While reader.Read
                    Response.Write("<windows-application>")
                    Response.Write("<name>")
                    Response.Write(reader.Item(0))
                    Response.Write("</name>")
                    Response.Write("<description>")
                    Response.Write(reader.Item(0))
                    Response.Write("</description>")
                    Response.Write("<service-ip>")
                    Response.Write(reader.Item(1))
                    Response.Write("</service-ip>")
                    Response.Write("<service-port>")
                    Response.Write(reader.Item(2))
                    Response.Write("</service-port>")
                    Response.Write("<command>")
                    Response.Write(reader.Item(3))
                    Response.Write("</command>")
                    Response.Write("</windows-application>")
                End While
                reader.Close()
                Response.Write("</windows-applications>")
                Response.Write("</applications>")
                Response.Write("</licence>")
                Response.Write("</cloud-application>")
                connection.Close()
            Catch ex As Exception
                'MsgBox(ex.ToString)
            End Try
        Else
            Response.Redirect("login.aspx")
        End If
    End Sub

End Class
