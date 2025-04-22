package br.com.casa_guido.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.casa_guido.ui.theme.BackgroundColor
import br.com.casa_guido.ui.theme.Paragraph

@Composable
fun RadioButtonMultOptValores(
    modifier: Modifier = Modifier,
    selected: Int?,
    labelTitulo: String,
    opcoesLista: List<Pair<String, Int>> = listOf("Sim" to 0, "Não" to 1),
    onClickListener: (Int) -> Unit
) {
    if (opcoesLista.size > 2) {
        Row {
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(bottom = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Paragraph),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = labelTitulo,
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = BackgroundColor,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                Spacer(Modifier.width(10.dp))

                Row(
                    Modifier.height(IntrinsicSize.Min)
                ) {
                    Row {
                        Column {
                            opcoesLista.forEachIndexed { index, item ->
                                if (index <= ((opcoesLista.size - 1) / 2)) {
                                    RadioButtonComLabelWidthIn(
                                        label = item.first,
                                        selected = selected == item.second,
                                        onClickListener = {
                                            onClickListener(item.second)
                                        }
                                    )
                                }
                            }
                        }

                        VerticalDivider(
                            color = Color.Black,
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )

                        Column {
                            opcoesLista.forEachIndexed { index, item ->
                                if (index > ((opcoesLista.size - 1) / 2)) {
                                    RadioButtonComLabelWidthIn(
                                        label = item.first,
                                        selected = selected == item.second,
                                        onClickListener = {
                                            onClickListener(item.second)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

    } else {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                Modifier
                    .widthIn(min = 80.dp)
                    .heightIn(min = 40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Paragraph),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = labelTitulo,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = BackgroundColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(10.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                opcoesLista.forEach { item ->
                    RadioButtonComLabel(
                        label = item.first,
                        selected = selected == item.second,
                        onClickListener = {
                            onClickListener(item.second)
                        }
                    )
                }
            }
        }
    }
}