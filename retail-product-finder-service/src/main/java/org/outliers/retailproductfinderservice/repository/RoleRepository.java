/*
 * Copyright (C) 2018-2019 Team Outliers <outliers@infosys.com>
 *
 * This file is part of Team Outliers.
 *
 * Team Outliers can not be copied and/or distributed without the express permission of Team Outliers
 *
 */

package org.outliers.retailproductfinderservice.repository;

import java.util.Optional;
import org.outliers.retailproductfinderservice.objects.model.Role;
import org.outliers.retailproductfinderservice.objects.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

  Optional<Role> findByName(RoleName roleName);
}
