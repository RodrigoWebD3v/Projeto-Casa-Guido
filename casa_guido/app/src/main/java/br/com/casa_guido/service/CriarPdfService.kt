package br.com.casa_guido.service

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.print.PrintAttributes
import android.print.pdf.PrintedPdfDocument
import br.com.casa_guido.R
import br.com.casa_guido.models.Paciente
import java.io.File
import java.io.FileOutputStream
import androidx.core.graphics.scale

class CriarPdfService {
    fun gerarFichaIdentificacaoPdf(context: Context, paciente: Paciente): File {
        val file = File(context.cacheDir, "ficha_identificacao.pdf")

        val printAttrs = PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.ISO_A4)
            .setResolution(PrintAttributes.Resolution("id", "pdf", 300, 300))
            .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
            .build()

        val pdf = PrintedPdfDocument(context, printAttrs)
        val page = pdf.startPage(0)
        val canvas = page.canvas

        val x = 40f
        var y = 40f
        val gap = 30f
        val width = canvas.width.toFloat() - x * 2

        val paint = Paint().apply {
            color = Color.BLACK
            textSize = 14f
            isAntiAlias = true
        }

        val boldPaint = Paint(paint).apply {
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        // Logo e cabeçalho
        val logo = BitmapFactory.decodeResource(context.resources, R.drawable.logo)
        val logoHeight = 90f
        val logoWidth = logo.width * (logoHeight / logo.height)
        canvas.drawBitmap(logo.scale(logoWidth.toInt(), logoHeight.toInt(), false), x, y, null)

        val rightX = canvas.width - x
        val infoPaint = Paint(paint).apply {
            textSize = 10f
            textAlign = Paint.Align.RIGHT
        }

        val headerInfo = listOf(
            "Rua Santo Antônio, 790 - Centro - Criciúma/SC",
            "CEP 88811-040 - (48) 3045.6211",
            "contato@guido.org.br - www.guido.org.br",
            "CNPJ: 12.927.890/0001-60"
        )
        var infoY = y + 10f
        headerInfo.forEach {
            canvas.drawText(it, rightX, infoY, infoPaint)
            infoY += 14f
        }

        y += logoHeight + 30f

        // Título
        boldPaint.textSize = 16f
        boldPaint.textAlign = Paint.Align.CENTER
        canvas.drawText("FICHA DE IDENTIFICAÇÃO", canvas.width / 2f, y, boldPaint)
        y += gap * 1.5f
        boldPaint.textAlign = Paint.Align.LEFT

        fun campo(label: String, valor: String, deslocamento: Float = 0f) {
            canvas.drawText("$label $valor", x + deslocamento, y, paint)
            y += gap
        }

        campo("PACIENTE:", paciente.pessoa.nome)
        campo("DATA DE NASCIMENTO:", paciente.pessoa.dataNascimento)
        campo("MÃE:", paciente.nomeMae)
        campo("PAI:", paciente.nomePai)
        campo("OUTRO:", paciente.nomeOutro)
        campo("TELEFONES:", paciente.pessoa.telefone)
        campo("ENDEREÇO:", paciente.pessoa.endereco.logradouro)

        // Bairro e cidade lado a lado
        canvas.drawText("BAIRRO: ${paciente.pessoa.endereco.bairro}", x, y, paint)
        canvas.drawText("CIDADE: ${paciente.pessoa.endereco.localidade}", x + width / 2, y, paint)
        y += gap

        campo("PRÓXIMO:", paciente.pessoa.endereco.referencia ?: "")

//        // Pré-cadastro e atualização na mesma linha
//        canvas.drawText("PRÉ-CADASTRO EM: ${paciente.dataCadastro ?: ""}", x, y, paint)
//        canvas.drawText("ATUALIZAÇÃO EM: ${paciente.dataAtualizacao ?: ""}", x + width / 2, y, paint)
//        y += gap

        // Caixa de diagnóstico
        val diagnostico = "DIAGNÓSTICO: ${paciente.diagnostico.uppercase()}"
        val boxTop = y - 20f
        val boxBottom = y + 20f
        val rectPaint = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 1.5f
        }
        canvas.drawRect(x, boxTop, x + width, boxBottom, rectPaint)
        canvas.drawText(diagnostico, x + 10, y + 5, paint)
        y += gap * 2

//        // Seção de profissionais
//        boldPaint.textSize = 15f
//        canvas.drawText("PROFISSIONAIS RESPONSÁVEIS", x, y, boldPaint)
//        y += gap
//
//        paint.textSize = 14f
//        campo("MÉDICA(O):", paciente.profissionalResponsavel.nome)
//        campo("ASSISTENTE SOCIAL ", "")
//        campo("PSICÓLOGA(O) ", "")
//        campo("OUTROS PROFISSIONAIS:", "")

        pdf.finishPage(page)

        val out = FileOutputStream(file)
        pdf.writeTo(out)
        out.close()
        pdf.close()

        return file
    }
}
