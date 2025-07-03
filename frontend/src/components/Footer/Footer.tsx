export default function Footer() {
    return (
        <footer className="w-full mt-auto text-center text-xs text-sidebartext bg-sidebarbg p-6 shadow-[4px_0_8px_rgba(0,0,0,0.2)]">
            © {new Date().getFullYear()} Sistema de Gestão de Pacientes - Todos os direitos reservados.
        </footer>
    );
}
