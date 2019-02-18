Public Class meisaiForm
    Private Sub btnReturn_Click(sender As Object, e As EventArgs) Handles btnReturn.Click
        viewForm.Show()
        Me.Close()
    End Sub

    Private Sub meisaiForm_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        Dim ddt = viewForm.ddt
        Dim rr = ddt.Rows(viewForm.intRow)
        Me.lblCode.Text = rr("コード")
        Me.lblName.Text = rr("名前")
        Me.lblDate.Text = rr("生年月日")
        Me.lblAge.Text = rr("年齢")
        Me.lblGroup.Text = rr("部門")
    End Sub
End Class