import { Predmet } from "./predmet";
export interface IshodPage<Ishod> {
    content: Ishod[];
  }
export interface Ishod {
    id:number;
    opis:String;
    predmet:Predmet[]
}
