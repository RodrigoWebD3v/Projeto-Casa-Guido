"use client";
import Sidebar from '@/components/Sidebar/sidebar';
import { useState } from "react";
import RadioButtonWithFixedLabel from "@/components/Button/RadioButtonWithFixedLabel";
import Link from 'next/link';
import {
  Home, LayoutDashboard, User, UserPlus, ChevronLeft, ChevronRight, Save, ChevronUp,
  ChevronDown
} from 'lucide-react';

const DOENCAS_ESQUERDA = [
  "Alcoolismo",
  "Deficiência Intelectual",
  "Deficiência Física",
  "Deficiência Auditiva",
  "Deficiência Visual",
  "Cardiopatia",
  "Asma",
  "Bronquite",
];
const DOENCAS_DIREITA = [
  "Hipert. arterial",
  "Toxoplasmose",
  "Drogadito",
  "Epilepsia",
  "Desnutrição",
  "Diabetes",
  "Câncer",
  "Outros",
];
const OPCOES_PROCURA = [
  "Hospital",
  "Unidade de Saúde",
  "Farmácia",
];

export default function HistoricoSaude() {
  const [doencasSelecionadas, setDoencasSelecionadas] = useState([]);
  const [procuraSelecionada, setProcuraSelecionada] = useState([]);
  const [cadastroAberto, setCadastroAberto] = useState(true);

  const toggleDoenca = (nome) => {
    setDoencasSelecionadas((prev) =>
      prev.includes(nome)
        ? prev.filter((d) => d !== nome)
        : [...prev, nome]
    );
  };

  const toggleProcura = (opcao) => {
    setProcuraSelecionada((prev) =>
      prev.includes(opcao)
        ? prev.filter((p) => p !== opcao)
        : [...prev, opcao]
    );
  };

  return (
    <div className="flex min-h-screen w-full text-main bg-background">
      <Sidebar />
      <div className="min-h-screen flex-1">
        <main className="p-6 mt-4">
          <div className="max-w-10xl mx-auto">
            <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
              Cadastro <UserPlus size={18} />
            </h1>
            <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
              {/* Cabeçalho fixo */}
              <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                <h2 className="text-xl text-darkgray font-semibold mb-2">9. Histórico de Saúde</h2>
                <h3 className="text-darkgray">Informações do histórico de saúde:</h3>
              </div>
              <div className="overflow-y-auto p-6 space-y-6 pr-2 flex-1">
                {/* Doenças existentes na família */}
                <div className="w-full h-[45px] mb-2 rounded-[10px] bg-success flex items-center justify-center">
                  <span className="text-background text-[16px] font-semibold text-center">
                    Doenças existentes na família
                  </span>
                </div>

                <div className="w-full flex flex-row gap-4 mb-2">
                  {/* Coluna Esquerda */}
                  <div className="flex flex-col flex-1">
                    {DOENCAS_ESQUERDA.map((nome, idx) => (
                      <div key={nome}>
                        <RadioButtonWithFixedLabel
                          label={nome}
                          selected={doencasSelecionadas.includes(nome)}
                          onClick={() => toggleDoenca(nome)}
                        />
                        {idx < DOENCAS_ESQUERDA.length - 1 && (
                          <div className="h-[1px] bg-graymedium hover:bg-graydark transition-colors my-1 w-full" />
                        )}
                      </div>
                    ))}
                  </div>

                  <div className="w-[1px] bg-greendark" />

                  {/* Coluna Direita */}
                  <div className="flex flex-col flex-1">
                    {DOENCAS_DIREITA.map((nome, idx) => (
                      <div key={nome}>
                        <RadioButtonWithFixedLabel
                          label={nome}
                          selected={doencasSelecionadas.includes(nome)}
                          onClick={() => toggleDoenca(nome)}
                        />
                        {idx < DOENCAS_DIREITA.length - 1 && (
                          <div className="h-[1px] bg-graymedium hover:bg-graydark transition-colors my-1 w-full" />
                        )}
                      </div>
                    ))}
                  </div>
                </div>

                {/* Em caso de doença, procura */}
                <div className="w-full h-[45px] my-2 rounded-[10px] bg-success flex items-center justify-center">
                  <span className="text-background text-[16px] font-semibold text-center">
                    Em caso de doença, procura
                  </span>
                </div>

                <div className="w-full flex flex-col gap-2 mb-2">
                  {OPCOES_PROCURA.map((opcao, idx) => (
                    <div key={opcao}>
                      <RadioButtonWithFixedLabel
                        label={opcao}
                        selected={procuraSelecionada.includes(opcao)}
                        onClick={() => toggleProcura(opcao)}
                      />
                      {idx < OPCOES_PROCURA.length - 1 && (
                        <div className="h-[1px] bg-graymedium hover:bg-graydark transition-colors my-1 w-full" />
                      )}
                    </div>
                  ))}
                </div>
              </div>


            </div>
          </div>
          {/* Botões de navegação */}
          <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
            <Link href="/cadastro/composicao-familiar" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
              <ChevronLeft size={18} /> Anterior
            </Link>

            <div className="flex-grow flex justify-center">
              <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                <Save size={18} /> Salvar
              </button>
            </div>

            <Link href="/cadastro/situacao-habitacional" className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm">
              Próximo <ChevronRight size={18} />
            </Link>
          </div>
        </main>
      </div>
    </div>
  );
}
