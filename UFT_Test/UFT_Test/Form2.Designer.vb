<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class meisaiForm
    Inherits System.Windows.Forms.Form

    'フォームがコンポーネントの一覧をクリーンアップするために dispose をオーバーライドします。
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Windows フォーム デザイナーで必要です。
    Private components As System.ComponentModel.IContainer

    'メモ: 以下のプロシージャは Windows フォーム デザイナーで必要です。
    'Windows フォーム デザイナーを使用して変更できます。  
    'コード エディターを使って変更しないでください。
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.labMesaiCode = New System.Windows.Forms.Label()
        Me.labMesaiName = New System.Windows.Forms.Label()
        Me.labMesaiBirthday = New System.Windows.Forms.Label()
        Me.labMesaiAge = New System.Windows.Forms.Label()
        Me.labMesaiGroup = New System.Windows.Forms.Label()
        Me.btnReturn = New System.Windows.Forms.Button()
        Me.lblCode = New System.Windows.Forms.TextBox()
        Me.lblName = New System.Windows.Forms.TextBox()
        Me.lblDate = New System.Windows.Forms.TextBox()
        Me.lblAge = New System.Windows.Forms.TextBox()
        Me.lblGroup = New System.Windows.Forms.TextBox()
        Me.SuspendLayout()
        '
        'labMesaiCode
        '
        Me.labMesaiCode.AutoSize = True
        Me.labMesaiCode.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.labMesaiCode.Location = New System.Drawing.Point(142, 64)
        Me.labMesaiCode.Name = "labMesaiCode"
        Me.labMesaiCode.Size = New System.Drawing.Size(52, 16)
        Me.labMesaiCode.TabIndex = 0
        Me.labMesaiCode.Text = "コード："
        '
        'labMesaiName
        '
        Me.labMesaiName.AutoSize = True
        Me.labMesaiName.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.labMesaiName.Location = New System.Drawing.Point(142, 105)
        Me.labMesaiName.Name = "labMesaiName"
        Me.labMesaiName.Size = New System.Drawing.Size(48, 16)
        Me.labMesaiName.TabIndex = 0
        Me.labMesaiName.Text = "名前："
        '
        'labMesaiBirthday
        '
        Me.labMesaiBirthday.AutoSize = True
        Me.labMesaiBirthday.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.labMesaiBirthday.Location = New System.Drawing.Point(142, 145)
        Me.labMesaiBirthday.Name = "labMesaiBirthday"
        Me.labMesaiBirthday.Size = New System.Drawing.Size(80, 16)
        Me.labMesaiBirthday.TabIndex = 0
        Me.labMesaiBirthday.Text = "生年月日："
        '
        'labMesaiAge
        '
        Me.labMesaiAge.AutoSize = True
        Me.labMesaiAge.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.labMesaiAge.Location = New System.Drawing.Point(142, 192)
        Me.labMesaiAge.Name = "labMesaiAge"
        Me.labMesaiAge.Size = New System.Drawing.Size(48, 16)
        Me.labMesaiAge.TabIndex = 0
        Me.labMesaiAge.Text = "年齢："
        '
        'labMesaiGroup
        '
        Me.labMesaiGroup.AutoSize = True
        Me.labMesaiGroup.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.labMesaiGroup.Location = New System.Drawing.Point(142, 239)
        Me.labMesaiGroup.Name = "labMesaiGroup"
        Me.labMesaiGroup.Size = New System.Drawing.Size(48, 16)
        Me.labMesaiGroup.TabIndex = 0
        Me.labMesaiGroup.Text = "部門："
        '
        'btnReturn
        '
        Me.btnReturn.Location = New System.Drawing.Point(488, 330)
        Me.btnReturn.Name = "btnReturn"
        Me.btnReturn.Size = New System.Drawing.Size(75, 23)
        Me.btnReturn.TabIndex = 1
        Me.btnReturn.Text = "戻る"
        Me.btnReturn.UseVisualStyleBackColor = True
        '
        'lblCode
        '
        Me.lblCode.BackColor = System.Drawing.SystemColors.Window
        Me.lblCode.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lblCode.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.lblCode.ForeColor = System.Drawing.SystemColors.InfoText
        Me.lblCode.Location = New System.Drawing.Point(268, 64)
        Me.lblCode.Name = "lblCode"
        Me.lblCode.Size = New System.Drawing.Size(100, 16)
        Me.lblCode.TabIndex = 2
        '
        'lblName
        '
        Me.lblName.BackColor = System.Drawing.SystemColors.Window
        Me.lblName.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lblName.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.lblName.ForeColor = System.Drawing.SystemColors.InfoText
        Me.lblName.Location = New System.Drawing.Point(268, 109)
        Me.lblName.Name = "lblName"
        Me.lblName.Size = New System.Drawing.Size(100, 16)
        Me.lblName.TabIndex = 2
        '
        'lblDate
        '
        Me.lblDate.BackColor = System.Drawing.SystemColors.Window
        Me.lblDate.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lblDate.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.lblDate.ForeColor = System.Drawing.SystemColors.InfoText
        Me.lblDate.Location = New System.Drawing.Point(268, 145)
        Me.lblDate.Name = "lblDate"
        Me.lblDate.Size = New System.Drawing.Size(100, 16)
        Me.lblDate.TabIndex = 2
        '
        'lblAge
        '
        Me.lblAge.BackColor = System.Drawing.SystemColors.Window
        Me.lblAge.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lblAge.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.lblAge.ForeColor = System.Drawing.SystemColors.InfoText
        Me.lblAge.Location = New System.Drawing.Point(268, 196)
        Me.lblAge.Name = "lblAge"
        Me.lblAge.Size = New System.Drawing.Size(100, 16)
        Me.lblAge.TabIndex = 2
        '
        'lblGroup
        '
        Me.lblGroup.BackColor = System.Drawing.SystemColors.Window
        Me.lblGroup.BorderStyle = System.Windows.Forms.BorderStyle.None
        Me.lblGroup.Font = New System.Drawing.Font("MS UI Gothic", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(128, Byte))
        Me.lblGroup.ForeColor = System.Drawing.SystemColors.InfoText
        Me.lblGroup.Location = New System.Drawing.Point(268, 239)
        Me.lblGroup.Name = "lblGroup"
        Me.lblGroup.Size = New System.Drawing.Size(100, 16)
        Me.lblGroup.TabIndex = 2
        '
        'meisaiForm
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 12.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(702, 397)
        Me.Controls.Add(Me.lblGroup)
        Me.Controls.Add(Me.lblAge)
        Me.Controls.Add(Me.lblDate)
        Me.Controls.Add(Me.lblName)
        Me.Controls.Add(Me.lblCode)
        Me.Controls.Add(Me.btnReturn)
        Me.Controls.Add(Me.labMesaiGroup)
        Me.Controls.Add(Me.labMesaiAge)
        Me.Controls.Add(Me.labMesaiBirthday)
        Me.Controls.Add(Me.labMesaiName)
        Me.Controls.Add(Me.labMesaiCode)
        Me.Name = "meisaiForm"
        Me.Text = "明細画面"
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents labMesaiCode As Label
    Friend WithEvents labMesaiName As Label
    Friend WithEvents labMesaiBirthday As Label
    Friend WithEvents labMesaiAge As Label
    Friend WithEvents labMesaiGroup As Label
    Friend WithEvents btnReturn As Button
    Friend WithEvents lblCode As TextBox
    Friend WithEvents lblName As TextBox
    Friend WithEvents lblDate As TextBox
    Friend WithEvents lblAge As TextBox
    Friend WithEvents lblGroup As TextBox
End Class
