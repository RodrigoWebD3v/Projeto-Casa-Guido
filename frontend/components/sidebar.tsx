"use client"

import Link from "next/link"
import { usePathname } from "next/navigation"
import { CalendarDays, Home, LogOut, Settings, Users } from "lucide-react"
import { cn } from "@/lib/utils"
import { Button } from "@/components/ui/button"

const sidebarLinks = [
  {
    name: "Dashboard",
    href: "/dashboard",
    icon: Home,
  },
  {
    name: "Pacientes",
    href: "/dashboard/patients",
    icon: Users,
  },
  {
    name: "Agendamentos",
    href: "/dashboard/appointments",
    icon: CalendarDays,
  },
  {
    name: "Configurações",
    href: "/dashboard/settings",
    icon: Settings,
  },
]

export function Sidebar() {
  const pathname = usePathname()

  return (
    <div className="hidden border-r bg-primary text-primary-foreground md:flex md:w-64 md:flex-col">
      <div className="flex flex-col h-full">
        <div className="flex h-16 items-center border-b border-primary-foreground/10 px-6">
          <Link href="/dashboard" className="flex items-center gap-2">
            <span className="text-xl font-bold">MediAgenda</span>
          </Link>
        </div>
        <div className="flex-1 overflow-auto py-2">
          <nav className="grid items-start px-2 text-sm">
            {sidebarLinks.map((link) => {
              const isActive = pathname === link.href || pathname.startsWith(`${link.href}/`)
              return (
                <Link
                  key={link.href}
                  href={link.href}
                  className={cn(
                    "flex items-center gap-3 rounded-lg px-3 py-2 transition-all hover:text-primary-foreground/80",
                    isActive ? "bg-primary-foreground/10 text-primary-foreground" : "text-primary-foreground/60",
                  )}
                >
                  <link.icon className="h-4 w-4" />
                  {link.name}
                </Link>
              )
            })}
          </nav>
        </div>
        <div className="border-t border-primary-foreground/10 p-4">
          <Button
            variant="outline"
            className="w-full justify-start border-primary-foreground/20 text-primary-foreground hover:bg-primary-foreground/10 hover:text-primary-foreground"
            asChild
          >
            <Link href="/">
              <LogOut className="mr-2 h-4 w-4" />
              Sair
            </Link>
          </Button>
        </div>
      </div>
    </div>
  )
}

