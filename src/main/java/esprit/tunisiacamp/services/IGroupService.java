package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.GroupCamp;

public interface IGroupService {
    GroupCamp saveGroup(Integer ReservationId,Integer userId);
    void acceptgroupe(Integer groupId);
}
