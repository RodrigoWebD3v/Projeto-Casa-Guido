'use client'

import { useState } from 'react'
import { X } from 'lucide-react'

export default function Quimio() {
  const [quimioSelecionado, setQuimioSelecionado] = useState('')
  const [dataInicio, setDataInicio] = useState('')
  const [dataFim, setDataFim] = useState('')
  const [quimios, setQuimios] = useState([])
  const [aviso, setAviso] = useState(false)

  const formatarData = (data) => {
    if (!data) return ''
    const [ano, mes, dia] = data.split('-')
    return `${dia}/${mes}/${ano}`
  }

  const adicionarQuimio = () => {
    if (!quimioSelecionado || (quimioSelecionado === 'Sim' && !dataInicio)) {
      return setAviso(true)
    }

    const novaQuimio = {
      dataInicio: quimioSelecionado === 'Sim' ? dataInicio : null,
      dataFim: quimioSelecionado === 'Sim' ? dataFim : null,
    }

    setQuimios([...quimios, novaQuimio])
    setQuimioSelecionado('')
    setDataInicio('')
    setDataFim('')
    setAviso(false)
  }

  const excluirQuimio = (index) => {
    const confirmar = window.confirm('Tem certeza que deseja excluir esta quimio?')
    if (!confirmar) return
    const novaLista = quimios.filter((_, idx) => idx !== index)
    setQuimios(novaLista)
  }

  const fecharAviso = () => setAviso(false)

  return (
    <div className="min-h-screen bg-[#00443f] py-10 px-6 text-white">
      <h1 className="text-2xl font-bold mb-4">Quimioterapia</h1>

      <div className="bg-white p-6 rounded-xl mb-6 text-[#0f2f29] shadow">
        {/* Seletor de quimio */}
        <div className="flex flex-col md:flex-row items-center gap-6 mb-4">
          <span className="bg-green-100 text-green-800 px-3 py-1 rounded font-semibold">
            Quimio
          </span>

          <div className="flex gap-4">
            <label className="flex items-center gap-2 text-base">
              <input
                type="radio"
                value="Sim"
                checked={quimioSelecionado === 'Sim'}
                onChange={() => setQuimioSelecionado('Sim')}
              />
              Sim
            </label>
            <label className="flex items-center gap-2 text-base">
              <input
                type="radio"
                value="Não"
                checked={quimioSelecionado === 'Não'}
                onChange={() => setQuimioSelecionado('Não')}
              />
              Não
            </label>
          </div>
        </div>

        {quimioSelecionado === 'Sim' && (
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
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
            onClick={adicionarQuimio}
            className="bg-[#0f2f29] text-white font-semibold py-2 px-6 rounded hover:bg-[#155347] transition"
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
      </div>

      <h2 className="text-xl font-semibold mb-2">Quimios cadastradas</h2>

      <div className="space-y-3">
        {quimios.map((item, idx) => (
          <div
            key={idx}
            className="bg-white p-4 rounded-lg flex justify-between items-center text-[#0f2f29] shadow"
          >
            <div>
              <p className="font-bold">Quimio {idx + 1}</p>
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
              onClick={() => excluirQuimio(idx)}
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
