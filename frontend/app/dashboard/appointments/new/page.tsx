"use client"

import type React from "react"

import { useState } from "react"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Textarea } from "@/components/ui/textarea"
import { Calendar } from "@/components/ui/calendar"
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover"
import { CalendarIcon, Loader2 } from "lucide-react"
import { format } from "date-fns"
import { ptBR } from "date-fns/locale"
import { useRouter } from "next/navigation"
import { cn } from "@/lib/utils"

export default function NewAppointmentPage() {
  const [date, setDate] = useState<Date>()
  const [isLoading, setIsLoading] = useState(false)
  const router = useRouter()

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    setIsLoading(true)

    // Simulando criação de agendamento
    setTimeout(() => {
      setIsLoading(false)
      router.push("/dashboard/appointments")
    }, 1500)
  }

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h1 className="text-3xl font-bold tracking-tight text-primary">Novo Agendamento</h1>
      </div>

      <Card>
        <form onSubmit={handleSubmit}>
          <CardHeader>
            <CardTitle>Agendar Consulta</CardTitle>
            <CardDescription>Preencha os dados para agendar uma nova consulta</CardDescription>
          </CardHeader>
          <CardContent className="space-y-6">
            <div className="space-y-4">
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="space-y-2">
                  <Label htmlFor="patient">Paciente</Label>
                  <Select required>
                    <SelectTrigger id="patient">
                      <SelectValue placeholder="Selecione um paciente" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="1">Ana Silva</SelectItem>
                      <SelectItem value="2">Carlos Oliveira</SelectItem>
                      <SelectItem value="3">Mariana Santos</SelectItem>
                      <SelectItem value="4">Pedro Costa</SelectItem>
                      <SelectItem value="5">Juliana Lima</SelectItem>
                    </SelectContent>
                  </Select>
                </div>

                <div className="space-y-2">
                  <Label htmlFor="type">Tipo de Consulta</Label>
                  <Select required>
                    <SelectTrigger id="type">
                      <SelectValue placeholder="Selecione o tipo" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="initial">Consulta Inicial</SelectItem>
                      <SelectItem value="followup">Retorno</SelectItem>
                      <SelectItem value="exam">Exame</SelectItem>
                      <SelectItem value="procedure">Procedimento</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="space-y-2">
                  <Label htmlFor="date">Data</Label>
                  <Popover>
                    <PopoverTrigger asChild>
                      <Button
                        variant={"outline"}
                        className={cn("w-full justify-start text-left font-normal", !date && "text-muted-foreground")}
                      >
                        <CalendarIcon className="mr-2 h-4 w-4" />
                        {date ? format(date, "PPP", { locale: ptBR }) : <span>Selecione uma data</span>}
                      </Button>
                    </PopoverTrigger>
                    <PopoverContent className="w-auto p-0">
                      <Calendar mode="single" selected={date} onSelect={setDate} initialFocus locale={ptBR} />
                    </PopoverContent>
                  </Popover>
                </div>

                <div className="space-y-2">
                  <Label htmlFor="time">Horário</Label>
                  <Select required>
                    <SelectTrigger id="time">
                      <SelectValue placeholder="Selecione um horário" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="0800">08:00</SelectItem>
                      <SelectItem value="0830">08:30</SelectItem>
                      <SelectItem value="0900">09:00</SelectItem>
                      <SelectItem value="0930">09:30</SelectItem>
                      <SelectItem value="1000">10:00</SelectItem>
                      <SelectItem value="1030">10:30</SelectItem>
                      <SelectItem value="1100">11:00</SelectItem>
                      <SelectItem value="1130">11:30</SelectItem>
                      <SelectItem value="1400">14:00</SelectItem>
                      <SelectItem value="1430">14:30</SelectItem>
                      <SelectItem value="1500">15:00</SelectItem>
                      <SelectItem value="1530">15:30</SelectItem>
                      <SelectItem value="1600">16:00</SelectItem>
                      <SelectItem value="1630">16:30</SelectItem>
                      <SelectItem value="1700">17:00</SelectItem>
                      <SelectItem value="1730">17:30</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
              </div>

              <div className="space-y-2">
                <Label htmlFor="notes">Observações</Label>
                <Textarea
                  id="notes"
                  placeholder="Adicione informações relevantes sobre a consulta"
                  className="min-h-[100px]"
                />
              </div>
            </div>
          </CardContent>
          <CardFooter className="flex justify-between">
            <Button variant="outline" type="button" onClick={() => router.back()}>
              Cancelar
            </Button>
            <Button type="submit" disabled={isLoading}>
              {isLoading ? (
                <>
                  <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                  Agendando...
                </>
              ) : (
                "Agendar Consulta"
              )}
            </Button>
          </CardFooter>
        </form>
      </Card>
    </div>
  )
}

