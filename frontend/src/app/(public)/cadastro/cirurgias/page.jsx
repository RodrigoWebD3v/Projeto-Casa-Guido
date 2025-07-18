'use client'

import { useState } from 'react'
import Link from 'next/link'
import {
  UserPlus, ChevronLeft, ChevronRight, Save, X
} from 'lucide-react'
import InputTextField from '@/components/TextField/InputTextField'
import DatePickerInput from '@/components/DatePicker/DatePicker'
import Sidebar from '@/components/Sidebar/sidebar';

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
    <div className="flex flex-col min-h-screen text-main bg-background">

      <div className="flex flex-1">
        {/* Menu lateral */}
        <Sidebar />

        {/* Conteúdo principal */}
        <main className="flex-1 p-6 mt-4">
          <div className="max-w-10xl mx-auto">
            <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
              Cadastro <UserPlus size={18} />
            </h1>

            <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
              {/* Cabeçalho fixo */}
              <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                <h2 className="text-xl text-darkgray font-semibold mb-2">3. Cirurgias</h2>
                <h3 className="text-darkgray">Informações de cirurgias do Paciente:</h3>
              </div>

              <div className="space-y-6 overflow-y-auto p-6 pr-2 flex-1">
                <div className="flex flex-col md:flex-row gap-6">
                  <div className="w-full md:w-1/3">
                    <InputTextField
                      nomeCampo="Nome da cirurgia"
                      valorPreenchido={nomeCirurgia}
                      onChange={setNomeCirurgia}
                      className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
                    />
                  </div>

                  <div className="w-full md:w-1/3">
                    <InputTextField
                      nomeCampo="CID"
                      valorPreenchido={cidCirurgia}
                      onChange={setCidCirurgia}
                      className="bg-graylight border border-graymedium rounded-md p-2 transition focus:outline-none focus:ring-2 focus:ring-greenstrong w-full"
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
                    className="bg-success text-background font-semibold py-2 px-6 rounded hover:bg-green transition"
                  >
                    Adicionar
                  </button>
                </div>

                {avisoData && (
                  <div className="bg-yellow-100 border-l-4 border-yellow-500 text-yellow-800 p-3 rounded flex justify-between items-center">
                    <span>Aviso: Já há uma cirurgia cadastrada para esse dia.</span>
                    <button onClick={fecharAviso} className="ml-4 hover:text-yellow-900">
                      <X size={16} />
                    </button>
                  </div>
                )}

                <div className="mt-6">
                  <h2 className="text-xl text-darkgray font-semibold mb-4">Cirurgias Cadastradas</h2>

                  {cirurgias.length === 0 ? (
                    <p className="text-darkgray">Nenhuma cirurgia cadastrada.</p>
                  ) : (
                    <div className="space-y-4">
                      {cirurgias.map((c, idx) => (
                        <div
                          key={idx}
                          className="bg-white p-4 rounded-lg flex justify-between items-center border border-graymedium text-darkgray shadow"
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
                  )}
                </div>
              </div>
            </div>

            {/* Botões finais */}
            <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
              <Link href="/cadastro/socio-economico" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                <ChevronLeft size={18} /> Anterior
              </Link>

              <div className="flex-grow flex justify-center">
                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                  <Save size={18} /> Salvar
                </button>
              </div>

              <Link href="/cadastro/quimio" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                Próximo <ChevronRight size={18} />
              </Link>
            </div>
          </div>
        </main>
      </div>

      {/* Footer */}
      <footer className="w-full mt-auto text-center text-xs text-sidebartext bg-sidebarbg p-6 shadow-[4px_0_8px_rgba(0,0,0,0.2)]">
        © {new Date().getFullYear()} Sistema de Gestão de Pacientes - Todos os direitos reservados.
      </footer>
    </div>
  )
}
