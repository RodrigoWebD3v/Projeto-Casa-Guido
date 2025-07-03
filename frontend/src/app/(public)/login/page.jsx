'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import InputTextField from '@/components/TextField/InputTextField';
import { Eye, EyeOff } from 'lucide-react';

export default function TelaLogin() {
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [mostrarSenha, setMostrarSenha] = useState(false);
    const [erro, setErro] = useState('');
    const [carregando, setCarregando] = useState(false);
    const router = useRouter();

    const handleLogin = (e) => {
        e.preventDefault();
        setErro('');
        setCarregando(true);

        if (email === 'admin@email.com' && senha === '123456') {
            alert('Login realizado com sucesso!');
            router.push('/dashboard');
        } else {
            setErro('E-mail ou senha inválidos');
        }

        setCarregando(false);
    };

    return (
        <div className="relative flex items-center justify-center min-h-screen overflow-hidden bg-black">
            <img
                src="/bg-login.jpg"
                alt="Fundo"
                className="absolute inset-0 w-full h-full object-cover object-center z-0"
            />

            <div className="absolute inset-0 bg-black bg-opacity-50 z-0" />

            <form
                onSubmit={handleLogin}
                className="relative z-10 bg-main bg-opacity-90 p-8 rounded-2xl shadow-lg w-full max-w-md flex flex-col gap-5 text-sidebarhoverbg"
            >
                <h1 className="text-3xl font-bold text-center mb-2">Login</h1>

                <InputTextField
                    nomeCampo="E-mail"
                    valorPreenchido={email}
                    onChange={setEmail}
                    className="w-full p-3 rounded border border-gray-300 text-greendark"
                />

                <div className="relative">
                    <InputTextField
                        nomeCampo="Senha"
                        type={mostrarSenha ? 'text' : 'password'}
                        valorPreenchido={senha}
                        onChange={setSenha}
                        className="w-full p-3 rounded border border-gray-300 text-greendark pr-10"
                    />

                    <button
                        type="button"
                        onClick={() => setMostrarSenha(!mostrarSenha)}
                        className="absolute top-1/2 right-3 -translate-y-1/2 text-gray-600 hover:text-gray-900"
                        aria-label={mostrarSenha ? 'Ocultar senha' : 'Mostrar senha'}
                    >
                        {mostrarSenha ? <EyeOff size={20} /> : <Eye size={20} />}
                    </button>
                </div>

                {erro && (
                    <p className="text-error text-sm text-center -mt-2">{erro}</p>
                )}

                <button
                    type="submit"
                    className="bg-button hover:bg-hoverBg text-greendark font-semibold py-2 px-4 rounded transition duration-300"
                >
                    {carregando ? 'Entrando...' : 'Entrar'}
                </button>
            </form>
        </div>
    );
}
