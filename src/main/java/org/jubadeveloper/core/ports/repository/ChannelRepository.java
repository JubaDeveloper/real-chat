package org.jubadeveloper.core.ports.repository;

import org.jubadeveloper.core.domain.channel.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {}
