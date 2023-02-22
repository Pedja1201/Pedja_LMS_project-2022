import { Ishod } from "./ishod";
import { TipEvaluacije } from "./tip-evaluacije";
export interface EvaluacijaZnanjaPage<EvaluacijaZnanja> {
    content: EvaluacijaZnanja[];
  }
export interface EvaluacijaZnanja {
    id:number;
    vremePocetka:Date;
    vremeZavrsetka:Date;
    ishod:Ishod[];
    tipEvaluacije:TipEvaluacije[]
}
