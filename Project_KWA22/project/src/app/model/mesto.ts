import { Drzava } from "./drzava";
export interface MestoPage<Mesto> {
    content: Mesto[];
  }
export interface Mesto {
    id:number;
    naziv:String;
    drzava:Drzava[]
}
