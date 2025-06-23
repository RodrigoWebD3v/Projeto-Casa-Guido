package br.com.casa_guido.screens.cadastro.formularios.documentos

import android.os.Build
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.models.Cirurgia
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.CamposCirurgia
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.CirurgiaViewModel
import br.com.casa_guido.screens.cadastro.formularios.cirurgia.itemCirurgia.ItemCirurgia
import br.com.casa_guido.screens.components.DataPicker
import br.com.casa_guido.screens.components.TextFieldSimples
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.GreenBlack
import br.com.casa_guido.ui.theme.Main
import br.com.casa_guido.ui.theme.Paragraph
import br.com.casa_guido.util.Utils
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

import androidx.compose.material.icons.filled.Delete
import br.com.casa_guido.models.Arquivo
import br.com.casa_guido.screens.cadastro.formularios.observacao.CamposObservacao

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AdicionarDocumentos(
    modifier: Modifier = Modifier,
    numeroTela: Int,
    documentos: MutableList<Arquivo>,
    onChangeCampo: (CamposDocumentos, Arquivo) -> Unit,
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(), onResult = { uri ->
            uri?.let {
                val cursor = context.contentResolver.query(uri, null, null, null, null)
                val nameIndex = cursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                var fileName = "Arquivo não encontrado"

                if (cursor != null && nameIndex != null && nameIndex != -1) {
                    cursor.moveToFirst()
                    fileName = cursor.getString(nameIndex)
                    cursor.close()
                }

                onChangeCampo(
                    CamposDocumentos.ADICIONA_ARQUIVO, Arquivo(
                        nome = fileName,
                        uri = uri.toString(),
                        conteudoArquivo = "",
                        pacienteId = "",
                    )
                )
            }
        })

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Paragraph)
            .padding(16.dp)
    ) {
        Text(
            "$numeroTela. Adicionar Documentos", style = TextStyle(
                fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold
            )
        )
        Text(
            "Adicionar documentos necessários", style = TextStyle(
                fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.Medium
            ), modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                launcher.launch(arrayOf("application/pdf", "image/*"))
            },
            colors = ButtonDefaults.buttonColors(containerColor = GreenBlack),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selecionar Documento")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (documentos.isNotEmpty()) {
            Text(
                text = "Documentos Selecionados:",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            documentos.forEachIndexed { index, arquivo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = arquivo.nome ?: "",
                        fontSize = 13.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remover documento",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                documentos.removeAt(index)
                            })
                }
            }
        }
    }
}
