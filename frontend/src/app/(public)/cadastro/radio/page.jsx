'use client'

import { useState } from 'react'
import Link from 'next/link'
import { Home, LayoutDashboard, User, ChevronLeft, ChevronRight, Save, X } from 'lucide-react'

export default function Radioterapia() {
  const [radioterapiaSelecionada, setRadioterapiaSelecionada] = useState('')
  const [dataInicio, setDataInicio] = useState('')
  const [dataFim, setDataFim] = useState('')
  const [radioterapias, setRadioterapias] = useState([])
  const [aviso, setAviso] = useState(false)

  const formatarData = (data) => {
    if (!data) return ''
    const [ano, mes, dia] = data.split('-')
    return `${dia}/${mes}/${ano}`
  }

  const adicionarRadioterapia = () => {
    if (!radioterapiaSelecionada || (radioterapiaSelecionada === 'Sim' && !dataInicio)) {
      return setAviso(true)
    }

    const novaRadioterapia = {
      dataInicio: radioterapiaSelecionada === 'Sim' ? dataInicio : null,
      dataFim: radioterapiaSelecionada === 'Sim' ? dataFim : null,
    }

    setRadioterapias([...radioterapias, novaRadioterapia])
    setRadioterapiaSelecionada('')
    setDataInicio('')
    setDataFim('')
    setAviso(false)
  }

  const excluirRadioterapia = (index) => {
    const confirmar = window.confirm('Tem certeza que deseja excluir esta radioterapia?')
    if (!confirmar) return
    const novaLista = radioterapias.filter((_, idx) => idx !== index)
    setRadioterapias(novaLista)
  }

  const fecharAviso = () => setAviso(false)

  return (
    <div className="flex min-h-screen text-main bg-background">

      {/* Menu lateral */}
      <aside
        className="w-44 bg-darkgray p-6"
        style={{ boxShadow: '4px 0 8px rgba(0, 0, 0, 0.2)' }}
      >
        <h2 className="text-xl font-bold mb-6 flex items-center gap-2 text-main">
          <Home size={18} /> <span>Menu</span>
        </h2>
        <nav className="flex flex-col gap-4">
          <Link
            href="/dashboard"
            className="flex items-center gap-2 p-2 rounded border border-transparent text-main hover:border-greendark hover:bg-button hover:text-greendark transition"
          >
            <LayoutDashboard size={18} /> <span>Dashboard</span>
          </Link>
          <Link
            href="/listagem-pacientes"
            className="flex items-center gap-2 p-2 rounded border border-transparent text-main hover:border-greendark hover:bg-button hover:text-greendark transition"
          >
            <User size={18} /> <span>Pacientes</span>
          </Link>
        </nav>
      </aside>

      {/* Conteúdo principal */}
      <main className="flex-1 p-6 mt-4">
        <div className="max-w-10xl mx-auto">
          <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">Cadastro de Radioterapia</h1>

          <div className="bg-offwhite p-6 rounded-lg shadow">
            <h2 className="text-xl text-background font-semibold mb-4">5. Radioterapia</h2>
            <h3 className="text-background mb-8">Informações sobre sessões de radioterapia:</h3>

            {/* Seletor */}
            <div className="flex flex-col md:flex-row items-center gap-6 mb-4">
              <span className="bg-green-100 text-background px-3 py-1 rounded font-semibold">
                Radioterapia
              </span>

              <div className="flex gap-4">
                <label className="flex items-center gap-2 text-background">
                  <input
                    type="radio"
                    value="Sim"
                    checked={radioterapiaSelecionada === 'Sim'}
                    onChange={() => setRadioterapiaSelecionada('Sim')}
                  />
                  Sim
                </label>
                <label className="flex items-center gap-2 text-background">
                  <input
                    type="radio"
                    value="Não"
                    checked={radioterapiaSelecionada === 'Não'}
                    onChange={() => setRadioterapiaSelecionada('Não')}
                  />
                  Não
                </label>
              </div>
            </div>

            {radioterapiaSelecionada === 'Sim' && (
              <div className=" text-background grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                <div>
                  <label className="block mb-1 font-medium">Data de início *</label>
                  <input
                    type="date"
                    className="w-full border rounded px-3 py-2"
                    value={dataInicio}
                    onChange={(e) => setDataInicio(e.target.value)}
                  />
                </div>
                <div>
                  <label className="block mb-1 font-medium">Data de fim (opcional)</label>
                  <input
                    type="date"
                    className="w-full border rounded px-3 py-2"
                    value={dataFim}
                    onChange={(e) => setDataFim(e.target.value)}
                  />
                </div>
              </div>
            )}

            <div className="flex justify-center">
              <button
                onClick={adicionarRadioterapia}
                className="bg-success text-background font-semibold py-2 px-6 rounded hover:bg-green transition"
              >
                Adicionar
              </button>
            </div>

            {aviso && (
              <div className="bg-yellow-100 border-l-4 border-yellow-500 text-yellow-800 p-3 rounded mt-4 flex justify-between items-start">
                <span>Aviso: Preencha os campos obrigatórios antes de adicionar.</span>
                <button onClick={fecharAviso} className="ml-4 hover:text-yellow-900">
                  <X size={16} />
                </button>
              </div>
            )}

            {/* Lista */}
            <div className="mt-6">
              <h2 className="text-xl text-darkgray font-semibold mb-4">Radioterapias Cadastradas</h2>

              {radioterapias.length === 0 ? (
                <p className="text-darkgray">Nenhuma radioterapia cadastrada.</p>
              ) : (
                <div className="space-y-4">
                  {radioterapias.map((item, idx) => (
                    <div
                      key={idx}
                      className="bg-white p-4 rounded-lg flex justify-between items-center border border-graymedium text-darkgray shadow"
                    >
                      <div>
                        <p className="font-bold">Radioterapia {idx + 1}</p>
                        {item.dataInicio ? (
                          <>
                            <p className="text-sm text-gray-600">Início: {formatarData(item.dataInicio)}</p>
                            <p className="text-sm text-gray-600">
                              {item.dataFim ? `Fim: ${formatarData(item.dataFim)}` : (
                                <span className="text-green-700 font-semibold">Em andamento</span>
                              )}
                            </p>
                          </>
                        ) : (
                          <p className="text-green-700 font-semibold">Não</p>
                        )}
                      </div>
                      <button
                        onClick={() => excluirRadioterapia(idx)}
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

            {/* Botões de navegação */}
            <div className="flex items-center mt-8 pt-6 border-t border-graymedium">
              <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                <ChevronLeft size={18} /> Anterior
              </button>

              <div className="flex-grow flex justify-center">
                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                  <Save size={18} /> Salvar
                </button>
              </div>

              <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
                Próximo <ChevronRight size={18} />
              </button>
            </div>
          </div>
        </div>
      </main>
    </div>
  )
}
