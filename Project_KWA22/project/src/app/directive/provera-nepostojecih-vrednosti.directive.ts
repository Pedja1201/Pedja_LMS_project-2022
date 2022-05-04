import { Directive, ElementRef, Input, SimpleChanges } from '@angular/core';

@Directive({
  selector: '[appProveraNepostojecihVrednosti]'
})
export class ProveraNepostojecihVrednostiDirective {

  @Input()
  appProveraNepostojecihVrednosti : any;

  @Input()
  bojaTeksta : any;

  @Input()
  bojaPozadine : any;

  private podrazumevanaBojaTeksta = "red";
  private podrazumevanaBojaPozadine = "gray";

  constructor(private element : ElementRef) {
  }
  
  ngOnChanges(changes: SimpleChanges): void {
    if(this.appProveraNepostojecihVrednosti == undefined){
      this.element.nativeElement.style.backgroundColor = this.bojaPozadine ? this.bojaPozadine : this.podrazumevanaBojaPozadine;
      this.element.nativeElement.style.color = this.bojaTeksta ? this.bojaTeksta : this.podrazumevanaBojaTeksta;
    }else{
      this.element.nativeElement.style.backgroundColor = "";
      this.element.nativeElement.style.color = "";
    }
  }
  


  ngOnInit(): void {
  
  }

}
