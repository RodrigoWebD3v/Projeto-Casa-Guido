import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import Link from "next/link"
import { CalendarDays, ClipboardList, Users } from "lucide-react"

export default function Home() {
  return (
    <div className="min-h-screen bg-background">
      <header className="bg-primary text-primary-foreground py-6">
        <div className="container flex justify-between items-center">
          <h1 className="text-2xl font-bold">MediAgenda</h1>
          <div className="space-x-2">
            <Button variant="secondary" asChild>
              <Link href="/login">Entrar</Link>
            </Button>
            <Button
              variant="outline"
              className="bg-transparent text-primary-foreground border-primary-foreground hover:bg-primary-foreground hover:text-primary"
              asChild
            >
              <Link href="/register">Cadastrar</Link>
            </Button>
          </div>
        </div>
      </header>

      <main className="container py-12">
        <section className="text-center mb-16">
          <h2 className="text-4xl font-bold mb-4 text-primary">Sistema de Agendamento de Pacientes</h2>
          <p className="text-lg max-w-2xl mx-auto text-foreground/80">
            Gerencie seus pacientes e agendamentos de forma simples e eficiente com o MediAgenda.
          </p>
          <div className="mt-8">
            <Button size="lg" className="bg-secondary text-secondary-foreground hover:bg-secondary/90" asChild>
              <Link href="/login">Começar Agora</Link>
            </Button>
          </div>
        </section>

        <section className="grid md:grid-cols-3 gap-8 mb-16">
          <Card>
            <CardHeader>
              <div className="w-12 h-12 rounded-full bg-primary/10 flex items-center justify-center mb-2">
                <Users className="h-6 w-6 text-primary" />
              </div>
              <CardTitle>Cadastro de Pacientes</CardTitle>
              <CardDescription>Gerencie informações dos pacientes</CardDescription>
            </CardHeader>
            <CardContent>
              <p className="text-foreground/70">
                Mantenha um registro completo dos seus pacientes com histórico médico, contatos e informações pessoais.
              </p>
            </CardContent>
          </Card>

          <Card>
            <CardHeader>
              <div className="w-12 h-12 rounded-full bg-primary/10 flex items-center justify-center mb-2">
                <CalendarDays className="h-6 w-6 text-primary" />
              </div>
              <CardTitle>Agendamento</CardTitle>
              <CardDescription>Calendário de consultas</CardDescription>
            </CardHeader>
            <CardContent>
              <p className="text-foreground/70">
                Agende consultas facilmente com visualização de calendário, evitando conflitos de horários.
              </p>
            </CardContent>
          </Card>

          <Card>
            <CardHeader>
              <div className="w-12 h-12 rounded-full bg-primary/10 flex items-center justify-center mb-2">
                <ClipboardList className="h-6 w-6 text-primary" />
              </div>
              <CardTitle>Histórico</CardTitle>
              <CardDescription>Acompanhamento de consultas</CardDescription>
            </CardHeader>
            <CardContent>
              <p className="text-foreground/70">
                Acesse o histórico completo de consultas e tratamentos de cada paciente.
              </p>
            </CardContent>
          </Card>
        </section>
      </main>

      <footer className="bg-primary text-primary-foreground py-8">
        <div className="container text-center">
          <p>© 2025 MediAgenda - Sistema de Agendamento de Pacientes</p>
        </div>
      </footer>
    </div>
  )
}

