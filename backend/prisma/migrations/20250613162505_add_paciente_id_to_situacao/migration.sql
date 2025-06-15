/*
  Warnings:

  - A unique constraint covering the columns `[paciente_id]` on the table `situacoes_habitacional` will be added. If there are existing duplicate values, this will fail.
  - Added the required column `paciente_id` to the `situacoes_habitacional` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "situacoes_habitacional" ADD COLUMN     "paciente_id" TEXT NOT NULL;

-- CreateIndex
CREATE UNIQUE INDEX "situacoes_habitacional_paciente_id_key" ON "situacoes_habitacional"("paciente_id");

-- AddForeignKey
ALTER TABLE "situacoes_habitacional" ADD CONSTRAINT "situacoes_habitacional_paciente_id_fkey" FOREIGN KEY ("paciente_id") REFERENCES "pacientes"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
