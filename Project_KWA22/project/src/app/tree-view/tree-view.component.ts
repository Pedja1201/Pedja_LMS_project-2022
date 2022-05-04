import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tree-view',
  templateUrl: './tree-view.component.html',
  styleUrls: ['./tree-view.component.css']
})
export class TreeViewComponent implements OnInit {
  tree = {value: null, childern: [
    {value: "element 1", childern: []},
    {value: "element 2", childern: [
      {value: "element 4", childern: [
        {value: "element 6", childern: []},
        {value: "element 7", childern: []}
      ]},
      {value: "element 5", childern: []},
    ]},
    {value: "element 3", childern: []}
  ]}; 

  ngOnInit(): void {
  }

}
