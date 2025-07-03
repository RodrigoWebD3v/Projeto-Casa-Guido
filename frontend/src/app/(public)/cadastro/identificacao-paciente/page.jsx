'use client';
import Sidebar from '@/components/Sidebar/sidebar';
import Link from 'next/link';
import {
    UserPlus,
    ChevronRight,
    Save,
} from 'lucide-react';
import { useState } from 'react';
import InputTextField from '@/components/TextField/InputTextField';
import SimpleTextField from '@/components/TextField/SimpleTextField';
import DatePickerInput from '@/components/DatePicker/DatePicker';

export default function CadastroPaciente() {
    const [nomeCompleto, setNomeCompleto] = useState('');
    const [dataNascimento, setDataNascimento] = useState('2025-04-22');
    const [mae, setMae] = useState('');
    const [pai, setPai] = useState('');
    const [outro, setOutro] = useState('');
    const [cpf, setCpf] = useState('');
    const [cpfInvalido, setCpfInvalido] = useState(false);
    const [rg, setRg] = useState('');
    const [rgInvalido, setRgInvalido] = useState(false);
    const [cartaoSus, setCartaoSus] = useState('');
    const [cartaoSusInvalido, setCartaoSusInvalido] = useState(false);
    const [celular, setCelular] = useState('');
    const [cadastroAberto, setCadastroAberto] = useState(true);

    function formatarCpf(valor) {
        const cpfNumeros = valor.replace(/\D/g, '').slice(0, 11);
        return cpfNumeros
            .replace(/^(\d{3})(\d)/, '$1.$2')
            .replace(/^(\d{3})\.(\d{3})(\d)/, '$1.$2.$3')
            .replace(/\.(\d{3})(\d)/, '.$1-$2');
    }

    function validarCpf(cpf) {
        const cpfLimpo = cpf.replace(/[^\d]+/g, '');
        if (cpfLimpo.length !== 11 || /^(\d)\1+$/.test(cpfLimpo)) return false;

        let soma = 0;
        for (let i = 0; i < 9; i++) soma += parseInt(cpfLimpo.charAt(i)) * (10 - i);
        let resto = (soma * 10) % 11;
        if (resto === 10 || resto === 11) resto = 0;
        if (resto !== parseInt(cpfLimpo.charAt(9))) return false;

        soma = 0;
        for (let i = 0; i < 10; i++) soma += parseInt(cpfLimpo.charAt(i)) * (11 - i);
        resto = (soma * 10) % 11;
        if (resto === 10 || resto === 11) resto = 0;
        return resto === parseInt(cpfLimpo.charAt(10));
    }

    function handleCpfChange(valor) {
        const formatado = formatarCpf(valor);
        setCpf(formatado);
        setCpfInvalido(formatado.length === 14 && !validarCpf(formatado));
    }

    function formatarRg(valor) {
        const numeros = valor.replace(/\D/g, '').slice(0, 9);
        return numeros
            .replace(/^(\d{2})(\d)/, '$1.$2')
            .replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3')
            .replace(/\.(\d{3})(\d)/, '.$1-$2');
    }

    function validarRg(rg) {
        const rgLimpo = rg.replace(/\D/g, '');
        if (rgLimpo.length !== 9) return false;
        if (/^(\d)\1+$/.test(rgLimpo)) return false; 
        return true; 
    }

    function handleRgChange(valor) {
        const formatado = formatarRg(valor);
        setRg(formatado);
        setRgInvalido(formatado.length === 13 && !validarRg(formatado)); // 13 com formatação
    }

    function formatarCartaoSus(valor) {
        const numeros = valor.replace(/\D/g, '').slice(0, 15);
        return numeros
            .replace(/^(\d{3})(\d)/, '$1 $2')
            .replace(/^(\d{3}) (\d{4})(\d)/, '$1 $2 $3')
            .replace(/^(\d{3}) (\d{4}) (\d{4})(\d)/, '$1 $2 $3 $4');
    }

    function validarCartaoSus(cartao) {
        const susLimpo = cartao.replace(/\D/g, '');
        return susLimpo.length === 15;
    }

    function handleCartaoSusChange(valor) {
        const formatado = formatarCartaoSus(valor);
        setCartaoSus(formatado);
        setCartaoSusInvalido(formatado.replace(/\D/g, '').length === 15 && !validarCartaoSus(formatado));
    }

    function formatarCelular(valor) {
        const numeros = valor.replace(/\D/g, '').slice(0, 11);
        return numeros
            .replace(/^(\d{2})(\d)/, '($1) $2')
            .replace(/^(\(\d{2}\)) (\d{5})(\d)/, '$1 $2-$3');
    }

    function handleCelularChange(valor) {
        setCelular(formatarCelular(valor));
    }

    return (
        <div className="flex flex-col min-h-screen text-main bg-background">
            <div className="flex flex-1">
                <Sidebar />

                <main className="flex-1 p-6 mt-4 overflow-auto">
                    <div className="max-w-10xl mx-auto flex flex-col">
                        <h1 className="flex items-center gap-2 text-2xl font-bold mb-6 text-center">
                            Cadastro <UserPlus size={18} />
                        </h1>

                        <div className="bg-offwhite rounded-lg shadow max-h-[calc(88vh-200px)] flex flex-col">
                            <div className="p-6 border-b border-graymedium bg-offwhite sticky top-0 z-10">
                                <h2 className="text-xl text-darkgray font-semibold mb-2">
                                    1. Identificação do Paciente
                                </h2>
                                <h3 className="text-darkgray">
                                    Informações pessoais e de contato do paciente:
                                </h3>
                            </div>

                            <div className="overflow-y-auto p-6 space-y-6 pr-2 flex-1">
                                <div className="bg-offwhite p-6 rounded-lg shadow">
                                    <SimpleTextField
                                        nomeCampo="Nome Completo"
                                        placeholder="Nome Completo"
                                        valorPreenchido={nomeCompleto}
                                        onChange={setNomeCompleto}
                                        className="w-full"
                                    />
                                </div>

                                <div className="bg-offwhite p-6 rounded-lg shadow">
                                    <DatePickerInput
                                        title="Data de Nascimento"
                                        value={dataNascimento}
                                        onChange={setDataNascimento}
                                        className="w-full"
                                    />
                                </div>

                                <div className="bg-offwhite p-6 rounded-lg shadow space-y-4">
                                    <SimpleTextField
                                        nomeCampo="Mãe"
                                        placeholder="Nome da mãe"
                                        valorPreenchido={mae}
                                        onChange={setMae}
                                        className="w-full"
                                    />
                                    <SimpleTextField
                                        nomeCampo="Pai"
                                        placeholder="Nome do pai"
                                        valorPreenchido={pai}
                                        onChange={setPai}
                                        className="w-full"
                                    />
                                    <SimpleTextField
                                        nomeCampo="Outro Responsável"
                                        placeholder="Digite o nome do responsável"
                                        valorPreenchido={outro}
                                        onChange={setOutro}
                                        className="w-full"
                                    />
                                </div>

                                <div className="bg-offwhite p-6 rounded-lg shadow">
                                    <div className="flex gap-6">
                                        <div className="w-1/2">
                                            <InputTextField
                                                nomeCampo="CPF"
                                                valorPreenchido={cpf}
                                                onChange={handleCpfChange}
                                                className={`w-full ${cpfInvalido ? 'border border-red-500' : ''}`}
                                            />
                                            {cpfInvalido && (
                                                <p className="text-red-500 text-sm mt-1">CPF inválido</p>
                                            )}
                                        </div>
                                        <div className="w-1/2">
                                            <InputTextField
                                                nomeCampo="RG"
                                                valorPreenchido={rg}
                                                onChange={handleRgChange}
                                                className={`w-full ${rgInvalido ? 'border border-red-500' : ''}`}
                                            />
                                            {rgInvalido && (
                                                <p className="text-red-500 text-sm mt-1">RG inválido</p>
                                            )}
                                        </div>
                                    </div>
                                </div>

                                <div className="bg-offwhite p-6 rounded-lg shadow">
                                    <div className="flex gap-6">
                                        <div className="w-1/2">
                                            <InputTextField
                                                nomeCampo="Cartão do SUS"
                                                valorPreenchido={cartaoSus}
                                                onChange={handleCartaoSusChange}
                                                className={`w-full ${cartaoSusInvalido ? 'border border-red-500' : ''}`}
                                            />
                                            {cartaoSusInvalido && (
                                                <p className="text-red-500 text-sm mt-1">Cartão SUS inválido</p>
                                            )}
                                        </div>
                                        <div className="w-1/2">
                                            <InputTextField
                                                nomeCampo="Celular"
                                                valorPreenchido={celular}
                                                onChange={handleCelularChange}
                                                className="w-full"
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div className="flex items-center mt-3 pt-6 border-t border-graymedium">
                            <div className="flex-grow flex justify-center">
                                <button className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green text-sm transition">
                                    <Save size={18} /> Salvar
                                </button>
                            </div>
                            <Link
                                href="/cadastro/socio-economico"
                                className="flex items-center gap-2 px-6 py-2 bg-success text-background rounded-md hover:bg-green transition text-sm"
                            >
                                Próximo <ChevronRight size={18} />
                            </Link>
                        </div>
                    </div>
                </main>
            </div>

            <footer className="w-full mt-auto text-center text-xs text-sidebartext bg-sidebarbg p-6 shadow-[4px_0_8px_rgba(0,0,0,0.2)]">
                © {new Date().getFullYear()} Sistema de Gestão de Pacientes - Todos os direitos reservados.
            </footer>
        </div>
    );
}
