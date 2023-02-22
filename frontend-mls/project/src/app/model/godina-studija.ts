import { Predmet } from "./predmet";
export interface GodinaStudijaPage<GodinaStudija> {
    content: GodinaStudija[];
  }
export interface GodinaStudija {
    id:number;
    godina:Date;
    predmet:Predmet[]
}
