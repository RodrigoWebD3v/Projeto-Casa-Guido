"use client"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Search, UserPlus } from "lucide-react"
import Link from "next/link"
import { Badge } from "@/components/ui/badge"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { PatientDialog } from "@/components/patient-dialog"

// Dados de exemplo
const patients = [
  {
    id: "1",
    name: "Ana Silva",
    email: "ana.silva@example.com",
    phone: "(11) 98765-4321",
    lastVisit: "10/03/2025",
    status: "Ativo",
  },
  {
    id: "2",
    name: "Carlos Oliveira",
    email: "carlos.oliveira@example.com",
    phone: "(11) 91234-5678",
    lastVisit: "05/03/2025",
    status: "Ativo",
  },
  {
    id: "3",
    name: "Mariana Santos",
    email: "mariana.santos@example.com",
    phone: "(11) 99876-5432",
    lastVisit: "28/02/2025",
    status: "Inativo",
  },
  {
    id: "4",
    name: "Pedro Costa",
    email: "pedro.costa@example.com",
    phone: "(11) 95555-4444",
    lastVisit: "15/02/2025",
    status: "Ativo",
  },
  {
    id: "5",
    name: "Juliana Lima",
    email: "juliana.lima@example.com",
    phone: "(11) 94444-3333",
    lastVisit: "01/02/2025",
    status: "Ativo",
  },
]

export default function PatientsPage() {
  const [searchTerm, setSearchTerm] = useState("")
  const [isDialogOpen, setIsDialogOpen] = useState(false)

  const filteredPatients = patients.filter(
    (patient) =>
      patient.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
      patient.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
      patient.phone.includes(searchTerm),
  )

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h1 className="text-3xl font-bold tracking-tight text-primary">Pacientes</h1>
        <Button
          className="bg-secondary text-secondary-foreground hover:bg-secondary/90"
          onClick={() => setIsDialogOpen(true)}
        >
          <UserPlus className="mr-2 h-4 w-4" />
          Novo Paciente
        </Button>
      </div>

      <Card>
        <CardHeader>
          <CardTitle>Gerenciar Pacientes</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="flex items-center gap-4 mb-6">
            <div className="relative flex-1">
              <Search className="absolute left-2.5 top-2.5 h-4 w-4 text-muted-foreground" />
              <Input
                type="search"
                placeholder="Buscar pacientes..."
                className="pl-8"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
              />
            </div>
          </div>

          <div className="rounded-md border">
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>Nome</TableHead>
                  <TableHead>Email</TableHead>
                  <TableHead>Telefone</TableHead>
                  <TableHead>Última Consulta</TableHead>
                  <TableHead>Status</TableHead>
                  <TableHead className="text-right">Ações</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {filteredPatients.length > 0 ? (
                  filteredPatients.map((patient) => (
                    <TableRow key={patient.id}>
                      <TableCell className="font-medium">{patient.name}</TableCell>
                      <TableCell>{patient.email}</TableCell>
                      <TableCell>{patient.phone}</TableCell>
                      <TableCell>{patient.lastVisit}</TableCell>
                      <TableCell>
                        <Badge variant={patient.status === "Ativo" ? "default" : "secondary"}>{patient.status}</Badge>
                      </TableCell>
                      <TableCell className="text-right">
                        <Button variant="ghost" size="sm" asChild>
                          <Link href={`/dashboard/patients/${patient.id}`}>Ver detalhes</Link>
                        </Button>
                      </TableCell>
                    </TableRow>
                  ))
                ) : (
                  <TableRow>
                    <TableCell colSpan={6} className="h-24 text-center">
                      Nenhum paciente encontrado.
                    </TableCell>
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </div>
        </CardContent>
      </Card>

      <PatientDialog open={isDialogOpen} onOpenChange={setIsDialogOpen} />
    </div>
  )
}

