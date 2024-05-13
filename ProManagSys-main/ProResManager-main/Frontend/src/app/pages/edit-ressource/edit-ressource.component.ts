import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RessourceService } from 'src/app/service/ressourceService';
import { ToastrService } from 'ngx-toastr';



@Component({
  selector: 'app-edit-ressource',
  templateUrl: './edit-ressource.component.html',
  styleUrls: ['./edit-ressource.component.scss']
})



export class EditRessourceComponent implements OnInit {

  ressourceToEdit: any = {};

  constructor(private route: ActivatedRoute, private ressourceService: RessourceService, private router: Router, private toastr: ToastrService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const ressourceId = +params['idRessource'];
      this.ressourceService.getRessourceById(ressourceId).subscribe(ressourceData => {
        this.ressourceToEdit = ressourceData;
      });
    });

  }

  onSubmit() {
    console.log(this.ressourceToEdit);
    this.ressourceService.updateRessource(this.ressourceToEdit, this.ressourceToEdit.idRessource).subscribe(
      updatedRessource => {
        console.log('Ressource updated:', updatedRessource);
        this.router.navigate(['ressource']);
        const messageFromApi = "ressource updated successfully ";
        this.toastr.success(messageFromApi);
      },
      (error: any) => {
        console.error('Error updating ressource:', error);
        const messageFromApi = error.error.message;
        this.toastr.error(messageFromApi);
      }
    );
  }

}
