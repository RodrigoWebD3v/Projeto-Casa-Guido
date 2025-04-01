"use client"

import type React from "react"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Textarea } from "@/components/ui/textarea"
import { Loader2 } from "lucide-react"

interface PatientDialogProps {
  open: boolean
  onOpenChange: (open: boolean) => void
}

export function PatientDialog({ open, onOpenChange }: PatientDialogProps) {
  const [isLoading, setIsLoading] = useState(false)

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    setIsLoading(true)

    // Simulando criação de paciente
    setTimeout(() => {
      setIsLoading(false)
      onOpenChange(false)
    }, 1500)
  }

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="sm:max-w-[600px]">
        <form onSubmit={handleSubmit}>
          <DialogHeader>
            <DialogTitle>Novo Paciente</DialogTitle>
            <DialogDescription>Preencha os dados do paciente. Clique em salvar quando terminar.</DialogDescription>
          </DialogHeader>

          <Tabs defaultValue="personal" className="mt-4">
            <TabsList className="grid w-full grid-cols-2">
              <TabsTrigger value="personal">Dados Pessoais</TabsTrigger>
              <TabsTrigger value="medical">Dados Médicos</TabsTrigger>
            </TabsList>

            <TabsContent value="personal" className="space-y-4 mt-4">
              <div className="grid grid-cols-2 gap-4">
                <div className="space-y-2">
                  <Label htmlFor="name">Nome Completo</Label>
                  <Input id="name" placeholder="Nome completo" required />
                </div>

                <div className="space-y-2">
                  <Label htmlFor="birthdate">Data de Nascimento</Label>
                  <Input id="birthdate" type="date" required />
                </div>
              </div>

              <div className="grid grid-cols-2 gap-4">
                <div className="space-y-2">
                  <Label htmlFor="email">E-mail</Label>
                  <Input id="email" type="email" placeholder="email@exemplo.com" />
                </div>

                <div className="space-y-2">
                  <Label htmlFor="phone">Telefone</Label>
                  <Input id="phone" placeholder="(00) 00000-0000" required />
                </div>
              </div>

              <div className="space-y-2">
                <Label htmlFor="address">Endereço</Label>
                <Input id="address" placeholder="Endereço completo" />
              </div>
            </TabsContent>

            <TabsContent value="medical" className="space-y-4 mt-4">
              <div className="space-y-2">
                <Label htmlFor="allergies">Alergias</Label>
                <Input id="allergies" placeholder="Listar alergias (se houver)" />
              </div>

              <div className="space-y-2">
                <Label htmlFor="medications">Medicamentos em Uso</Label>
                <Input id="medications" placeholder="Listar medicamentos (se houver)" />
              </div>

              <div className="space-y-2">
                <Label htmlFor="medicalHistory">Histórico Médico</Label>
                <Textarea
                  id="medicalHistory"
                  placeholder="Informações relevantes sobre o histórico médico do paciente"
                  className="min-h-[100px]"
                />
              </div>
            </TabsContent>
          </Tabs>

          <DialogFooter className="mt-6">
            <Button type="button" variant="outline" onClick={() => onOpenChange(false)}>
              Cancelar
            </Button>
            <Button type="submit" disabled={isLoading}>
              {isLoading ? (
                <>
                  <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                  Salvando...
                </>
              ) : (
                "Salvar Paciente"
              )}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  )
}

