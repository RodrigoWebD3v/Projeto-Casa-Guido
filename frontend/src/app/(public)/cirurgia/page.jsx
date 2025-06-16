'use client'

import { useState } from 'react'
import InputTextField from '@/components/TextField/InputTextField'
import DatePickerInput from '@/components/DatePicker/DatePicker'
import { X } from 'lucide-react'

export default function Cirurgias() {
  const [nomeCirurgia, setNomeCirurgia] = useState('')
  const [cidCirurgia, setCidCirurgia] = useState('')
  const [dataCirurgia, setDataCirurgia] = useState('')
  const [cirurgias, setCirurgias] = useState([])
  const [avisoData, setAvisoData] = useState(false)

  const formatarData = (data) => {
    if (!data) return ''
    const date = new Date(data)
    const dia = String(date.getDate()).padStart(2, '0')
    const mes = String(date.getMonth() + 1).padStart(2, '0')
    const ano = date.getFullYear()
    return `${dia}/${mes}/${ano}`
  }

  const adicionarCirurgia = () => {
    if (!nomeCirurgia || !dataCirurgia || !cidCirurgia) return

    const dataFormatada = formatarData(dataCirurgia)

    const existeCirurgiaMesmoDia = cirurgias.some(
      c => c.data === dataFormatada
    )

    if (existeCirurgiaMesmoDia) {
      setAvisoData(true)
      return
    }

    setCirurgias([
      ...cirurgias,
      {
        nome: nomeCirurgia,
        cid: cidCirurgia,
        data: dataFormatada
      }
    ])

    setNomeCirurgia('')
    setCidCirurgia('')
    setDataCirurgia('')
    setAvisoData(false)
  }

  const excluirCirurgia = (index) => {
    const confirmar = window.confirm('Tem certeza que deseja excluir esta cirurgia?')
    if (!confirmar) return
    const novaLista = cirurgias.filter((_, idx) => idx !== index)
    setCirurgias(novaLista)
  }

  const fecharAviso = () => {
    setAvisoData(false)
  }

  return (
    <div className="min-h-screen bg-[#00443f] py-10 px-6 text-white">
      <h1 className="text-2xl font-bold mb-4">Cirurgias</h1>

      <div className="bg-white p-6 rounded-xl mb-6 text-[#0f2f29] shadow">
        <div className="flex flex-col md:flex-row gap-4 mb-4">
          <div className="w-full md:w-2/3 flex flex-col gap-4">
            <InputTextField
              nomeCampo="Nome da cirurgia"
              valorPreenchido={nomeCirurgia}
              onChange={setNomeCirurgia}
            />
            <InputTextField
              nomeCampo="CID"
              valorPreenchido={cidCirurgia}
              onChange={setCidCirurgia}
            />
          </div>
          <div className="w-full md:w-1/3">
            <DatePickerInput
              title="Data da cirurgia"
              value={dataCirurgia}
              onChange={setDataCirurgia}
            />
          </div>
        </div>

        <div className="flex justify-center">
          <button
            onClick={adicionarCirurgia}
            className="bg-[#0f2f29] text-white font-semibold py-2 px-6 rounded hover:bg-[#155347] transition"
          >
            Adicionar
          </button>
        </div>

        {avisoData && (
          <div className="bg-yellow-100 border-l-4 border-yellow-500 text-yellow-800 p-3 rounded mt-4 flex justify-between items-start">
            <span>Aviso: Já há uma cirurgia cadastrada para esse dia.</span>
            <button onClick={fecharAviso} className="ml-4 hover:text-yellow-900">
              <X size={16} />
            </button>
          </div>
        )}
      </div>

      <h2 className="text-xl font-semibold mb-2">Cirurgias cadastradas</h2>

      <div className="space-y-3">
        {cirurgias.map((c, idx) => (
          <div
            key={idx}
            className="bg-white p-4 rounded-lg flex justify-between items-center text-[#0f2f29] shadow"
          >
            <div>
              <p className="font-bold">{c.nome}</p>
              <p className="text-sm text-gray-600">CID: {c.cid}</p>
              <p className="text-sm text-gray-600">Data: {c.data}</p>
            </div>
            <button
              onClick={() => excluirCirurgia(idx)}
              className="text-red-600 hover:text-red-800"
              title="Remover"
            >
              <X size={20} />
            </button>
          </div>
        ))}
      </div>
    </div>
  )
}
