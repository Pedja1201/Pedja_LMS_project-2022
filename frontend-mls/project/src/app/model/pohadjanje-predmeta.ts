import { RealizacijaPredmeta } from "./realizacija-predmeta";
import { Student } from "./student";
export interface PohadjanjePredmetaPage<PohadjanjePredmeta> {
    content: PohadjanjePredmeta[];
  }
export interface PohadjanjePredmeta {
    id:number;
    konacnaOcena:number;
    realizacijaPredmeta:RealizacijaPredmeta[];
    student:Student[]
}
