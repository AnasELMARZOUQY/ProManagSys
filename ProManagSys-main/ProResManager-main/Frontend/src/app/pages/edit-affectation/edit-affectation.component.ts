import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AffectationService } from '../../service/affectationService';
import { Router } from '@angular/router';
import { natureAffectationService } from '../../service/natureAffectationService';
import { statutAffectationService } from '../../service/statutAffectationService'

@Component({
  selector: 'app-edit-affectation',
  templateUrl: './edit-affectation.component.html',
  styleUrls: ['./edit-affectation.component.scss']
})
export class EditAffectationComponent implements OnInit {

  affectationToEdit: any = {};
  statutAffectations: any[] = []; 
  natureAffectations: any[] = []; 
  constructor(private route: ActivatedRoute, private affectationService: AffectationService, private router: Router, private natureAffectationService: natureAffectationService, private statutAffectationService: statutAffectationService) { }

  ngOnInit() {
    this.fetchNatureAffectations();
    this.fetchStatutAffectations();

    this.route.params.subscribe(params => {
      const affectationId = +params['idAffectation'];
      this.affectationService.getAffectationById(affectationId).subscribe(affectationData => {
        this.affectationToEdit = affectationData;
      });
    });
  }

  onSubmit() {
    this.affectationService.updateAffectation(this.affectationToEdit, this.affectationToEdit.idAffectation).subscribe(
      updatedAffectation => {
        console.log('Affectation updated:', updatedAffectation);
        this.router.navigate(['tables'])
      },
      error => {
        console.error('Error updating Affectation:', error);
      }
    );
  }


  fetchNatureAffectations() {
    this.natureAffectationService.getAllNatureAffectation().subscribe((data: any[]) => {
      this.natureAffectations = data;
    },
    error => {
      console.error('Error fetching nature affectations', error);
    }
    
    );
  }
  

  fetchStatutAffectations() {
    this.statutAffectationService.getAllStatutAffectation().subscribe((data: any[]) => {
      this.statutAffectations = data;
    },
    error => {
      console.error('Error fetching staut affectations', error);
    });
  }


}
